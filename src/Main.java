import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static BlockingQueue<String> maxA = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> maxB = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> maxC = new ArrayBlockingQueue<>(100);
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                try {
                    String text = generateText("abc", 100_000);
                    maxA.put(text);
                    maxB.put(text);
                    maxC.put(text);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String maxString = "";
            int maxInt = 0;
            for (int i = 0; i < 10_000; i++) {
                try {
                    String text = maxA.take();
                    if (text.length()-text.replaceAll("a","").length()>maxInt){
                        maxString = text;
                    }
//                    else {
//                        try {
//                            System.out.println(maxA.take());
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
                System.out.println("Строка с максимальным значением A: " + maxString);
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String maxString = "";
            int maxInt = 0;
            for (int i = 0; i < 10_000; i++) {
                try {
                    String text = maxB.take();
                    if (text.length()-text.replaceAll("b","").length()>maxInt){
                        maxString = text;
                    }
//                    else {
//                        try {
//                            System.out.println(maxA.take());
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
                System.out.println("Строка с максимальным значением B: " + maxString);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String maxString = "";
            int maxInt = 0;
            for (int i = 0; i < 10_000; i++) {
                try {
                    String text = maxC.take();
                    if (text.length()-text.replaceAll("c","").length()>maxInt){
                        maxString = text;
                    }
//                    else {
//                        try {
//                            System.out.println(maxA.take());
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

                System.out.println("Строка с максимальным значением C: " + maxString);

        }).start();

    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

}
