package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.apiHandling.PopUp;
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
        System.setProperty("https.protocols", "TLSv1 TLSv1.1 TLSv1.2 TLSv1.3");
        long timer1 = System.nanoTime();
        int count = 0;
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for (final String url : urls) {
            this.url = url;
            futures.add(executorService.submit(this));
            count++;
        }
        for (Future<String> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                System.out.printf("Sorry we couldn´t download this url: %s, because their server denied us access. PepoSadFace", this.url + System.lineSeparator());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long timer2 = System.nanoTime();
        System.out.printf("Parallel took: %dms\n", (timer2 - timer1) / 1000000);

        String message = "Parallel took: " + (timer2 - timer1) / 1000000 + "ms";
        PopUp.createNotification(message);
        return count;
    }

    @Override
    public String call() throws Exception {
        return saveUrl2File(url);
    }
}
