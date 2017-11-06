package lab08;

/**
 * Implementation of a generator that produces a very non-random sequence of
 * numbers.
 */
public class PoorRandomNumberGenerator implements RandomNumberGenerator {

  public int nextInt(int max) {
    return 1;
  }

  public void setSeed(long seed) {
  }

  public void setConstants(long multipler, long increment) {
  }
  
}
