package com.example.aptapi.launcher;


import com.example.aptapi.template.IBindHelper;

public class AutoBind {
    private static AutoBind instance = null;

    public AutoBind() {
    }

    public static AutoBind getInstance() {
        if (instance == null) {
            synchronized (AutoBind.class) {
                if (instance == null) {
                    instance = new AutoBind();
                }
            }
        }
        return instance;
    }

    public void inject(Object target) {
        String className = target.getClass().getCanonicalName();
        String helperName = className + "$$Autobind";
        try {
            IBindHelper helper = (IBindHelper) (Class.forName(helperName).getConstructor().newInstance());
            helper.inject(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}