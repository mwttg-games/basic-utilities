package io.github.mwttg.games;

import java.util.concurrent.atomic.AtomicInteger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class DotTestListener extends TestListenerAdapter {

  private final AtomicInteger counter = new AtomicInteger(0);

  @Override
  public void onTestFailure(final ITestResult tr) {
    print("F");
  }

  @Override
  public void onTestSkipped(final ITestResult tr) {
    print("S");
  }

  @Override
  public void onTestSuccess(final ITestResult tr) {
    print(".");
  }

  @Override
  public void onFinish(final ITestContext testContext) {
    System.out.println();
  }

  private void print(final String character) {
    System.out.print(character);
    final var x = counter.incrementAndGet();
    if (x % 40 == 0) {
      System.out.println(); // new line after 40 characters
      counter.set(0);
    }
  }

}
