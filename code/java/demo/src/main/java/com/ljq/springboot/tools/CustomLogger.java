package com.ljq.springboot.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomLogger {
    static private Logger log = LoggerFactory.getLogger("");

    static public void info(String s){
        log.info(s);
    }

    static public void startMethodLog(){
        // Thread.currentThread().getStackTrace()[1]是你当前方法执行堆栈
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement currentClass = stackTrace[2];
        StackTraceElement invokedClass = stackTrace[3];

        String className =currentClass.getClassName();
        String methodName = currentClass.getMethodName();
        int    methodLine = currentClass.getLineNumber()-1;
        String fileName = currentClass.getFileName();
        String invokedMethodName = invokedClass.getMethodName();
        String invokedClassName = invokedClass.getClassName();
        int    invokedmethodLine = invokedClass.getLineNumber();
        String invokedFileName = invokedClass.getFileName();
        log.info(
                 "\n>>>>>> CLASS: " + className + "\n"
                +"       METHOD: " + methodName + "    LINE: " + methodLine + "\n"
                +"       FILE: " + fileName +"\n"
                +"INVOKED BY: \n"
                +"       CLASS: " + invokedClassName + "\n"
                +"       METHOD: " + invokedMethodName + "    LINE: " + invokedmethodLine + "\n"
                +"       FILE: " + invokedFileName +"\n"
        );

    }

    static public void endMethodLog(){
        StackTraceElement currentTrace = Thread.currentThread().getStackTrace()[2];
        String className = currentTrace.getClassName();
        String methodName = currentTrace.getMethodName();
        log.info(
                 "\n<<<<<< CLASS: " + className + "\n"
                +"         METHOD: " + methodName + "\n"
        );
    }

    static public void methodLogInCatch(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement currentClass = stackTrace[2];
        StackTraceElement invokedClass = stackTrace[3];

        String className =currentClass.getClassName();
        String methodName = currentClass.getMethodName();
        String fileName = currentClass.getFileName();
        log.info(
                "\n<<<<<< [CATCH] "
               +"       CLASS: " + className + "\n"
               +"       METHOD: " + methodName + "\n"
               +"       FILE: " + fileName +"\n"
        );
    }


}
