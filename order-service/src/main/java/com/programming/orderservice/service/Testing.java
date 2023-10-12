package com.programming.orderservice.service;

public class Testing {
    public static void main(String[] args) {
//        String s=null;
//        String s1=null;
//        if(s == null){
//            System.out.println("asdfasdfasdfs");        }
//        if(s.isEmpty() && s==null  ){
//            System.out.println("erroor");
//        } else if (s1.isEmpty() && s1==null) {
//            System.out.println("second error");
//        } else{
//            System.out.println("working");
//        }


//        String str = " , India,     Is My    Country";
//        //1st way
//       // String noSpaceStr = str.replaceAll("\\s", ""); // using built in method
//        String noSpaceStr= str.trim();
//        System.out.println(noSpaceStr);

//        String str = "India, China, Bhutan, ";
//        str = str.replace(",","");
//        System.out.println(str);

//        String names = ",India, China, Bhutan, ";
//
//        String after = names.trim().replaceAll(" +", " ");
//       // names = names.replaceAll(", $", "");
//
//       // regex.Pattern (" , (]").compile ().matcher (names).replaceAll (",");
//        System.out.println(after);
//        String input = ",,,  ,Exa    mple,o   f,a,      line,";
//
//        // Remove starting comma
////        String result = input.replaceFirst(/("^,+)\(,+$)", "");
////        System.out.println("Output: " + result);
////        // Remove last comma
////        result = result.replaceAll(",$", "");
////        result=result.replaceAll("\\s", "");
//       String str = input. replaceAll(", $", "");
//        System.out.println(str);

        String inputString = ",,,Hello,World,,,";

        for (int i = 0; i<=inputString.length(); i++) {
            // Remove the first comma
            if (inputString.startsWith(",")) {
                inputString = inputString.substring(1);
            }

            // Remove the last comma
            if (inputString.endsWith(",")) {
                inputString = inputString.substring(0, inputString.length() - 1);
            }
        }

        System.out.println(inputString);



    }
}
