package lab08;

public class BetterRandomNumberGenerator implements RandomNumberGenerator {
	
	private long multiplier;
	private long increment;
	private long seed;

	@Override
	public int nextInt(int max) {
		seed = (seed * multiplier) + increment;
		seed = Math.abs(seed); // This is a hack!
		return (int) (seed % max);
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;
	}

	@Override
	public void setConstants(long multiplier, long increment) {
		this.multiplier = multiplier;
		this.increment = increment;
	}
}
