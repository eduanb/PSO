package benchmark;

import java.util.LinkedList;
import java.util.Random;

import pso.Location;
import pso.PSOConstants;
import pso.Particle;
import pso.Velocity;

public class Bohachevsky1Problem implements PSOConstants, ProblemSet
{
	public static final double ERR_TOLERANCE = 1E-20;
	private final int PROBLEM_DIMENSION = 2;
	private final double LOC_HIGH = 50;
	private final double LOC_LOW = -50;
	private final double VEL_LOW = -1;
	private final double VEL_HIGH = 1;
	private final double TARGET_VALUE = 0;
	@Override
	public double evaluate(Location location)
	{
		double X1 = location.getLoc()[0];
		double X2 = location.getLoc()[1];
		double result = X1 * X1;
		result += 2 * (X2 * X2);
		result += -0.3 * Math.cos(3 * Math.PI * X1);
		result += -0.4 * Math.cos(4 * Math.PI * X2);
		result += 0.7;
		
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
			double[] loc = new double[PROBLEM_DIMENSION];
			for(int j = 0; j < PROBLEM_DIMENSION; j++)
			{
				loc[j] = LOC_LOW + generator.nextDouble() * (LOC_HIGH - LOC_LOW);
			}
			Location location = new Location(loc);

			// randomize velocity in the range defined in Problem Set
			double[] vel = new double[PROBLEM_DIMENSION];
			for(int j = 0; j < PROBLEM_DIMENSION; j++)
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
		return PROBLEM_DIMENSION;
	}

	@Override
	public double getTargetValue()
	{
		return TARGET_VALUE;
	}
	@Override
	public double[] getClampMaxValues()
	{
		double[] result = new double[PROBLEM_DIMENSION];
		for(int i = 0; i < PROBLEM_DIMENSION; i++)
		{
			result[i] = VEL_HIGH;
		}
		return result;
	}

	@Override
	public double[] getClampMinValues()
	{
		double[] result = new double[PROBLEM_DIMENSION];
		for(int i = 0; i < PROBLEM_DIMENSION; i++)
		{
			result[i] = VEL_HIGH;
		}
		return result;
	}
	
	@Override
	public String getName()
	{
		return "Bohachevsky1Problem";
	}
}
