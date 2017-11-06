package lab08;

/**
 * Builds a RNG simply by wrapping the java.util.Random class.
 */
public class JavasRandomNumberGenerator implements RandomNumberGenerator {

  /*
   * Create a Java.util.Random object to do the actual "work" of this class.
   */
  private java.util.Random javaRandom = new java.util.Random();

  public int nextInt(int max) {
    return this.javaRandom.nextInt(max);
  }

  public void setSeed(long seed) {
    this.javaRandom.setSeed(seed);
  }

  public void setConstants(long multiplier, long increment) {
    // not needed
  }
}
