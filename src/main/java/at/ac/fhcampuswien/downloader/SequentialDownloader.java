package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.exceptions.NewsAPIExceptionLeo;

import java.util.List;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class SequentialDownloader extends Downloader {

    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) throws NewsAPIExceptionLeo {
        long timer1 = System.nanoTime();
        int count = 0;
        for (String url : urls) {
            try {
                String fileName = saveUrl2File(url);
                if(fileName != null)
                    count++;
            } catch (NewsAPIExceptionLeo e){
                System.err.println(e.getMessage());
                throw new NewsAPIExceptionLeo(e.getMessage());
            } catch (Exception e){
                throw new NewsAPIExceptionLeo("Different problem occurred in " + this.getClass().getName() + ". Message: " + e.getMessage());
            }
        }
        long timer2 = System.nanoTime();
        System.out.printf("Sequentiell took: %dms\n", (timer2-timer1)/1000000);
        return count;
    }
}