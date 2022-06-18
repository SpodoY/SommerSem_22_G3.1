package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.exceptions.NewsAPIExceptionLeo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


// Class is needed for exercise 4 - ignore for exercise 3 solution
public class ParallelDownloader extends Downloader implements Callable<String> {

    private String url;


    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) throws NewsAPIExceptionLeo {
        // TODO implement download function using multiple threads
        long timer1 = System.nanoTime();
        final int[] count = {0};
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for (final String url : urls){
            futures.add(executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String answer = saveUrl2File(url);
                    count[0]++;
                    return answer;
                }
            }));
        }
        for (Future<String> future : futures){
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        long timer2 = System.nanoTime();
        System.out.printf("Parallel took: %dms\n", (timer2-timer1)/1000000);
        return count[0];
    }
    @Override
    public String call() throws Exception {
        return saveUrl2File(url);
    }
}