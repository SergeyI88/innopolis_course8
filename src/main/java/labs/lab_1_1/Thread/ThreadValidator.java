package labs.lab_1_1.Thread;

import labs.lab_1_1.*;
import labs.lab_1_1.Reader;

import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadValidator extends Thread  {
    private ConcurrentLinkedQueue<String> resourseArrayList;
    private ConcurrentLinkedQueue<Resourse> queueWasCheckingResourse;

    public ThreadValidator(ConcurrentLinkedQueue<String> resourseArrayList, ConcurrentLinkedQueue<Resourse> queueWasCheckingresourse) {
        this.resourseArrayList = resourseArrayList;
        this.queueWasCheckingResourse = queueWasCheckingresourse;
    }

    @Override
    public void run() {
        String r = resourseArrayList.poll();
        ReadableResourse reader = (ReadableResourse) Proxy.newProxyInstance(ReaderInvocation.class.getClassLoader(),
                new Class[]{ReadableResourse.class},
                new ReaderInvocation(new Reader(r)));
        Resourse resourse = new Resourse();
        if (ClassContainsMethodsForChecking.whatIsIT(r)) {
            resourse.setText(reader.readFile(r));
            if (ClassContainsMethodsForChecking.readResource(resourse.getText())) {
                resourse.setName(r);
                queueWasCheckingResourse.offer(resourse);
            }
        } else if (ClassContainsMethodsForChecking.mayBeUrl(r)) {
            String res = reader.readUrl(r);
           // if (!res.equals("")) {
                resourse.setText(res);
            //}
            if (ClassContainsMethodsForChecking.readResource(resourse.getText())) {
                resourse.setName(r);
                queueWasCheckingResourse.offer(resourse);
            }
        }

    }
}
