package pso;

// this is the heart of the PSO program
// the code is for 2-dimensional space problem
// but you can easily modify it to solve higher dimensional space problem

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import benchmark.ProblemSet;

public class RandomResetAllClampedGbestPSO implements PSO,PSOConstants
{
	Random generator;

	public RandomResetAllClampedGbestPSO()
	{
		generator = new Random();
	}

	@Override
	public void execute(ProblemSet problemSet)
	{
		List<Particle> swarm;
		double[] pBest = new double[SWARM_SIZE];
		List<Location> pBestLocation = new LinkedList<Location>();
		double gBest = 0;
		Location gBestLocation = null;
		double[] fitnessValueList = new double[SWARM_SIZE];
		swarm = problemSet.initializeSwarm();
		updateFitnessList(fitnessValueList,swarm);

		for (int i = 0; i < SWARM_SIZE; i++)
		{
			pBest[i] = fitnessValueList[i];
			pBestLocation.add(swarm.get(i).getLocation());
		}

		int t = 0;
		double w;
		double err = Double.MAX_VALUE;

		while (t < MAX_ITERATION && err > problemSet.getErrTolarance())
		{
			// step 1 - update pBest
			for (int i = 0; i < SWARM_SIZE; i++)
			{
				if (fitnessValueList[i] < pBest[i])
				{
					pBest[i] = fitnessValueList[i];
					pBestLocation.set(i, swarm.get(i).getLocation());
				}
			}

			// step 2 - update gBest
			int bestParticleIndex = PSOUtility.getMinPos(fitnessValueList);
			if (t == 0 || fitnessValueList[bestParticleIndex] < gBest)
			{
				gBest = fitnessValueList[bestParticleIndex];
				gBestLocation = swarm.get(bestParticleIndex).getLocation();
			}

			w = 1;//W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);

			for (int i = 0; i < SWARM_SIZE; i++)
			{
				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();

				Particle p = swarm.get(i);

				// step 3 - update velocity
				double[] newVel = new double[problemSet.getProblemDimention()];
				for (int j = 0; j < problemSet.getProblemDimention(); j++)
				{
					newVel[j] = (w * p.getVelocity().getPos()[j]) + (r1 * C1)
										* (pBestLocation.get(i).getLoc()[j] - p.getLocation().getLoc()[j]) + (r2 * C2)
										* (gBestLocation.getLoc()[j] - p.getLocation().getLoc()[j]);
					if(newVel[j] > problemSet.getClampMaxValues()[j])
					{
						for (int a = 0; a < problemSet.getProblemDimention(); a++)
						{
							newVel[a] = (-problemSet.getClampMaxValues()[a]) + generator.nextDouble() * (problemSet.getClampMaxValues()[a]);
						}
						break;
					}
				}
				Velocity vel = new Velocity(newVel);
				p.setVelocity(vel);

				// step 4 - update location
				double[] newLoc = new double[problemSet.getProblemDimention()];

				for (int j = 0; j < problemSet.getProblemDimention(); j++)
				{
					newLoc[j] = p.getLocation().getLoc()[j] + newVel[j];
				}
				Location loc = new Location(newLoc);
				p.setLocation(loc);
			}

			err = problemSet.evaluate(gBestLocation) - problemSet.getTargetValue(); // minimizing the functions means it's getting closer to 0

			System.out.println("ITERATION " + t + ": ");
			for (int j = 0; j < problemSet.getProblemDimention(); j++)
			{
				System.out.println("     Best X" + (j + 1) + ": " + gBestLocation.getLoc()[j]);
			}
			System.out.println("     Value: " + problemSet.evaluate(gBestLocation));

			t++;
			updateFitnessList(fitnessValueList,swarm);
		}

		System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
		for (int j = 0; j < problemSet.getProblemDimention(); j++)
		{
			System.out.println("     Best X" + (j + 1) + ": " + gBestLocation.getLoc()[j]);
		}
	}

	private double[] updateFitnessList(double[] fitnessValueList, List<Particle> swarm)
	{
		for (int i = 0; i < SWARM_SIZE; i++)
		{
			fitnessValueList[i] = swarm.get(i).getFitnessValue();
		}
		return fitnessValueList;
	}
}
