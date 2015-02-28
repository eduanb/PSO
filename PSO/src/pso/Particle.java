package pso;

import benchmark.ProblemSet;

public class Particle
{
	private double fitnessValue;
	private Velocity velocity;
	private Location location;
	private ProblemSet problem;

	public Particle()
	{
		super();
	}

	public Particle(double fitnessValue, Velocity velocity, Location location, ProblemSet prob)
	{
		super();
		this.fitnessValue = fitnessValue;
		this.velocity = velocity;
		this.location = location;
		this.problem = prob;
	}

	public Velocity getVelocity()
	{
		return velocity;
	}

	public void setVelocity(Velocity velocity)
	{
		this.velocity = velocity;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}
	public void setProblem(ProblemSet problem)
	{
		this.problem = problem;
	}
	public double getFitnessValue()
	{
		fitnessValue = problem.evaluate(location);
		return fitnessValue;
	}
}
