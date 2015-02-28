package benchmark;

import java.util.LinkedList;
import java.util.Random;

import pso.Location;
import pso.PSOConstants;
import pso.Particle;
import pso.Velocity;

public class SphericalProblem implements ProblemSet, PSOConstants
{

	public static final double ERR_TOLERANCE = 1E-20;
	private int problemDimention;
	private final double LOC_HIGH = 100;
	private final double LOC_LOW = -100;
	private final double VEL_LOW = -1.5;
	private final double VEL_HIGH = 1.5;
	private final double TARGET_VALUE = 0;
	
	public SphericalProblem(int dimention)
	{
		this.problemDimention = dimention;
	}
	
	@Override
	public double evaluate(Location location)
	{
		double[] loc = location.getLoc();
		double result = 0;
		for(int i = 0; i < problemDimention; i++)
		{
			result += loc[i] * loc[i];
		}
		return result;
	}

	@Override
	public double getErrTolarance()
	{
		return ERR_TOLERANCE;
	}

	@Override
	public LinkedList<Particle> initializeSwarm()
	{
		LinkedList<Particle> result = new LinkedList<Particle>();
		Random generator = new Random();
		Particle p;
		for (int i = 0; i < SWARM_SIZE; i++)
		{
			p = new Particle();

			// randomize location inside a space defined in Problem Set
			double[] loc = new double[problemDimention];
			for (int j = 0; j < problemDimention; j++)
			{
				loc[j] = LOC_LOW + generator.nextDouble() * (LOC_HIGH - LOC_LOW);
			}
			Location location = new Location(loc);

			// randomize velocity in the range defined in Problem Set
			double[] vel = new double[problemDimention];
			for (int j = 0; j < problemDimention; j++)
			{
				vel[j] = VEL_LOW + generator.nextDouble() * (VEL_HIGH - VEL_LOW);
			}
			Velocity velocity = new Velocity(vel);

			p.setLocation(location);
			p.setVelocity(velocity);
			p.setProblem(this);
			result.add(p);
		}
		return result;
	}

	@Override
	public int getProblemDimention()
	{
		return problemDimention;
	}

	@Override
	public double getTargetValue()
	{
		return TARGET_VALUE;
	}
	@Override
	public double[] getClampMaxValues()
	{
		double[] result = new double[problemDimention];
		for(int i = 0; i < problemDimention; i++)
		{
			result[i] = VEL_HIGH;
		}
		return result;
	}

	@Override
	public double[] getClampMinValues()
	{
		double[] result = new double[problemDimention];
		for(int i = 0; i < problemDimention; i++)
		{
			result[i] = VEL_HIGH;
		}
		return result;
	}
	
	@Override
	public String getName()
	{
		return "Spherical";
	}
}
