package pso;

import java.util.LinkedList;

import benchmark.Bohachevsky1Problem;
import benchmark.ColvilleProblem;
import benchmark.EasomProblem;
import benchmark.ProblemSet;
import benchmark.SphericalProblem;
import benchmark.TwoDimentionMathProblem;

public class Main
{
	public static void main(String args[])
	{
		Long start = System.currentTimeMillis();
		
		LinkedList<ProblemSet> ProblemList = new LinkedList<ProblemSet>();
		ProblemList.add(new Bohachevsky1Problem());
		ProblemList.add(new ColvilleProblem());
		ProblemList.add(new EasomProblem());
		ProblemList.add(new SphericalProblem(5));
		// ...

		LinkedList<PSO> PSOList = new LinkedList<PSO>();
		PSOList.add(new GbestPSO());
		PSOList.add(new ClampedGbestPSO());
		PSOList.add(new RandomResetClampedGbestPSO());
		PSOList.add(new RandomResetAllClampedGbestPSO());
		PSOList.add(new ZeroClampedGbestPSO());
		PSOList.add(new ZeroAllClampedGbestPSO());
		// ...
		
		for(PSO pso : PSOList)
		{
			for(ProblemSet problemSet : ProblemList)
			{
				System.out.println("problem:" + problemSet.getName());
				pso.execute(problemSet);
			}
		}
		
		long end = System.currentTimeMillis();
		
		double timeInSec = (double)(end - start)/1000;
		System.out.println("Total time:" + timeInSec);
	}
}
