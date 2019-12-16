package com.baiguoqing.bottomtools;

import android.app.Activity;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 全屏模式下适配工具类
 */
class FullScreenWindowTools {

    private static final String TAG = FullScreenWindowTools.class.getSimpleName();
    private Activity mActivity;
    private View mView;

    FullScreenWindowTools(Activity activity, View view) {
        mActivity = activity;
        mView = view;
    }

    void fit() {
        /*方法未定，带探索*/
        /*
        IBinder InputMethodService = ServiceManager.getService(Context.CLIPBOARD_SERVICE);
        String IInputMethod = "android.content.IClipboard";//com.android.internal.view.IInputMethodManager

        if (InputMethodService != null) {
            IBinder hookInputMethodService =
                    (IBinder) Proxy.newProxyInstance(IBinder.class.getClassLoader(),
                            new Class[]{IBinder.class},
                            new ServiceHook(InputMethodService, IInputMethod, true, new InputMethodHookHandler()));
            ServiceManager.setService(Context.CLIPBOARD_SERVICE, hookInputMethodService);
        } else {
            Log.e(TAG, "InputMethodService hook failed!");
        }*/
    }

    static class ServiceManager {

        private static Method sGetServiceMethod;
        private static Map<String, IBinder> sCacheService;
        private static Class c_ServiceManager;

        static {
            try {
                c_ServiceManager = Class.forName("android.os.ServiceManager");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        static IBinder getService(String serviceName) {
            if (c_ServiceManager == null) {
                return null;
            }

            if (sGetServiceMethod == null) {
                try {
                    sGetServiceMethod = c_ServiceManager.getDeclaredMethod("getService", String.class);
                    sGetServiceMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            if (sGetServiceMethod != null) {
                try {
                    return (IBinder) sGetServiceMethod.invoke(null, serviceName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        static void setService(String serviceName, IBinder service) {
            if (c_ServiceManager == null) {
                return;
            }

            if (sCacheService == null) {
                try {
                    Field sCache = c_ServiceManager.getDeclaredField("sCache");
                    sCache.setAccessible(true);
                    sCacheService = (Map<String, IBinder>) sCache.get(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            sCacheService.remove(serviceName);
            sCacheService.put(serviceName, service);
        }
    }

    class ServiceHook implements InvocationHandler {
        private static final String TAG = "ServiceHook";

        private IBinder mBase;
        private Class<?> mStub;
        private Class<?> mInterface;
        private InvocationHandler mInvocationHandler;

        ServiceHook(IBinder mBase, String iInterfaceName, boolean isStub, InvocationHandler InvocationHandler) {
            this.mBase = mBase;
            this.mInvocationHandler = InvocationHandler;

            try {
                this.mInterface = Class.forName(iInterfaceName);
                this.mStub = Class.forName(String.format("%s%s", iInterfaceName, isStub ? "$Stub" : ""));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("queryLocalInterface".equals(method.getName())) {
                return Proxy.newProxyInstance(proxy.getClass().getClassLoader(), new Class[]{mInterface},
                        new HookHandler(mBase, mStub, mInvocationHandler));
            }

            Log.e(TAG, "ERROR!!!!! method:name = " + method.getName());
            return method.invoke(mBase, args);
        }

        class HookHandler implements InvocationHandler {
            private Object mBase;
            private InvocationHandler mInvocationHandler;

            HookHandler(IBinder base, Class<?> stubClass,
                        InvocationHandler InvocationHandler) {
                mInvocationHandler = InvocationHandler;

                try {
                    Method asInterface = stubClass.getDeclaredMethod("asInterface", IBinder.class);
                    this.mBase = asInterface.invoke(null, base);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (mInvocationHandler != null) {
                    return mInvocationHandler.invoke(mBase, method, args);
                }
                return method.invoke(mBase, args);
            }
        }
    }

    class InputMethodHookHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("BGQ HAHA");
            return method.invoke(proxy, args);
        }
    }
}
