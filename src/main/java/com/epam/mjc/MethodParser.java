package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] s = signatureString.split(" ");
        String args = signatureString.substring(signatureString.indexOf("(") + 1, signatureString.indexOf(")"));

        if (args.isBlank() || args.isEmpty()) {
            if (s[0].equals("public") || s[0].equals("private") || s[0].equals("protected")) {
                String accessModifier = s[0];
                String returnType = s[1];
                String methodName = s[2].substring(0, s[2].indexOf("("));
                List<MethodSignature.Argument> arguments = new ArrayList<>();
                MethodSignature methodSignature = new MethodSignature(methodName, arguments);
                methodSignature.setReturnType(returnType);
                methodSignature.setAccessModifier(accessModifier);
                return methodSignature;
            } else {
                String returnType = s[0];
                String methodName = s[1];
                List<MethodSignature.Argument> arguments = new ArrayList<>();
                MethodSignature methodSignature = new MethodSignature(methodName, arguments);
                methodSignature.setReturnType(returnType);
                return methodSignature;
            }
        }
        if (s[0].equals("public") || s[0].equals("private") || s[0].equals("protected")) {

            String accessModifier = s[0];
            String returnType = s[1];
            String methodNameAndArgs = s[2] + " " + s[3];
            String methodName = methodNameAndArgs.substring(0, methodNameAndArgs.indexOf("("));

            List<MethodSignature.Argument> arguments = new ArrayList<>();

            if (args.contains(",")) {
                String[] split = args.split(", ");
                for (String s1 : split) {
                    String[] argsList = s1.split(" ");
                    String type = argsList[0];
                    String name = argsList[1];
                    MethodSignature.Argument argument = new MethodSignature.Argument(type, name);
                    arguments.add(argument);
                }
            } else {
                String[] argsList = args.split(" ");
                String type = argsList[0];
                String name = argsList[1];
                MethodSignature.Argument argument = new MethodSignature.Argument(type, name);
                arguments.add(argument);
            }
            MethodSignature methodSignature = new MethodSignature(methodName, arguments);
            methodSignature.setReturnType(returnType);
            methodSignature.setAccessModifier(accessModifier);
            return methodSignature;
        } else {
            String returnType = s[0];
            String methodNameAndArgs = s[1] + " " + s[2];
            String methodName = methodNameAndArgs.substring(0, methodNameAndArgs.indexOf("("));

            List<MethodSignature.Argument> arguments = new ArrayList<>();

            if (args.contains(",")) {
                String[] split = args.split(", ");
                for (String s1 : split) {
                    String[] argsList = s1.split(" ");
                    String type = argsList[0];
                    String name = argsList[1];
                    MethodSignature.Argument argument = new MethodSignature.Argument(type, name);
                    arguments.add(argument);
                }
            } else {
                String[] argsList = args.split(" ");
                String type = argsList[0];
                String name = argsList[1];
                MethodSignature.Argument argument = new MethodSignature.Argument(type, name);
                arguments.add(argument);
            }
            MethodSignature methodSignature = new MethodSignature(methodName, arguments);
            methodSignature.setReturnType(returnType);
            return methodSignature;
        }
    }

    public static void main(String[] args) {
        MethodParser methodParser = new MethodParser();
        MethodSignature methodSignature = methodParser.parseFunction("public float mark()");
        System.out.println(methodSignature.getAccessModifier());
        System.out.println(methodSignature.getReturnType());
        System.out.println(methodSignature.getMethodName());
    }
}
