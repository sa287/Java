package ticTacToe;


import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/**
 * A policy iteration agent. You should implement the following methods:
 * (1) {@link PolicyIterationAgent#evaluatePolicy}: this is the policy evaluation step from your lectures
 * (2) {@link PolicyIterationAgent#improvePolicy}: this is the policy improvement step from your lectures
 * (3) {@link PolicyIterationAgent#train}: this is a method that should runs/alternate (1) and (2) until convergence. 
 * 
 * NOTE: there are two types of convergence involved in Policy Iteration: Convergence of the Values of the current policy, 
 * and Convergence of the current policy to the optimal policy.
 * The former happens when the values of the current policy no longer improve by much (i.e. the maximum improvement is less than 
 * some small delta). The latter happens when the policy improvement step no longer updates the policy, i.e. the current policy 
 * is already optimal. The algorithm should stop when this happens.
 * 
 * @author ae187
 *
 */
public class PolicyIterationAgent extends Agent {

	/**
	 * This map is used to store the values of states according to the current policy (policy evaluation). 
	 */
	HashMap<Game, Double> policyValues=new HashMap<Game, Double>();
	
	/**
	 * This stores the current policy as a map from {@link Game}s to {@link Move}. 
	 */
	HashMap<Game, Move> curPolicy=new HashMap<Game, Move>();
	
	double discount=0.9;
	
	/**
	 * The mdp model used, see {@link TTTMDP}
	 */
	TTTMDP mdp;
	
	/**
	 * loads the policy from file if one exists. Policies should be stored in .pol files directly under the project folder.
	 */
	public PolicyIterationAgent() {
		super();
		this.mdp=new TTTMDP();
		initValues();
		initRandomPolicy();
		train();
		
		
	}
	
	
	/**
	 * Use this constructor to initialise your agent with an existing policy
	 * @param p
	 */
	public PolicyIterationAgent(Policy p) {
		super(p);
		
	}

	/**
	 * Use this constructor to initialise a learning agent with default MDP paramters (rewards, transitions, etc) as specified in 
	 * {@link TTTMDP}
	 * @param discountFactor
	 */
	public PolicyIterationAgent(double discountFactor) {
		
		this.discount=discountFactor;
		this.mdp=new TTTMDP();
		initValues();
		initRandomPolicy();
		train();
	}
	/**
	 * Use this constructor to set the various parameters of the Tic-Tac-Toe MDP
	 * @param discountFactor
	 * @param winningReward
	 * @param losingReward
	 * @param livingReward
	 * @param drawReward
	 */
	public PolicyIterationAgent(double discountFactor, double winningReward, double losingReward, double livingReward, double drawReward)
	{
		this.discount=discountFactor;
		this.mdp=new TTTMDP(winningReward, losingReward, livingReward, drawReward);
		initValues();
		initRandomPolicy();
		train();
	}
	/**
	 * Initialises the {@link #policyValues} map, and sets the initial value of all states to 0 
	 * (V0 under some policy pi ({@link #curPolicy} from the lectures). Uses {@link Game#inverseHash} and {@link Game#generateAllValidGames(char)} to do this. 
	 * 
	 */
	public void initValues()
	{
		List<Game> allGames=Game.generateAllValidGames('X');//all valid games where it is X's turn, or it's terminal.
		for(Game g: allGames)
			this.policyValues.put(g, 0.0);
		
	}
	
	/**
	 *  You should implement this method to initially generate a random policy, i.e. fill the {@link #curPolicy} for every state. Take care that the moves you choose
	 *  for each state ARE VALID. You can use the {@link Game#getPossibleMoves()} method to get a list of valid moves and choose 
	 *  randomly between them. 
	 */
	public void initRandomPolicy()
	{
		
		int randomGen = ThreadLocalRandom.current().nextInt();
		Move genMove;
		
		
		
		
		for(Game state: policyValues.keySet()) {
			
			
			
			if(state.isTerminal()) {
				policyValues.replace(state, 0.0);
				continue;
			}
			randomGen = ThreadLocalRandom.current().nextInt(state.getPossibleMoves().size());
			genMove = state.getPossibleMoves().get(randomGen);
			curPolicy.put(state, genMove);
	
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	/**
	 * Performs policy evaluation steps until the maximum change in values is less than {@code delta}, in other words
	 * until the values under the currrent policy converge. After running this method, 
	 * the {@link PolicyIterationAgent#policyValues} map should contain the values of each reachable state under the current policy. 
	 * You should use the {@link TTTMDP} {@link PolicyIterationAgent#mdp} provided to do this.
	 *
	 * @param delta
	 */
	protected void evaluatePolicy(double delta)
	{
		HashMap<Game, Double> previous = policyValues;
		boolean converge = false;
		double sum = 0;
		double maxChange = 0;
		Move action; 
		
		
		do {
			
			for(Game state: policyValues.keySet()) {
				sum = 0;
				action = curPolicy.get(state);
                if (state.isTerminal()) {
                    policyValues.replace(state, 0.0);
                    continue;
                }
                
                
                for(TransitionProb probability:mdp.generateTransitions(state, action)) {
                	Outcome x = probability.outcome;
					double tempCalc = x.localReward + discount * policyValues.get(x.sPrime);
					double calculation = probability.prob * tempCalc;
					sum = sum + calculation;
                }
                
                
                policyValues.replace(state, sum);
                
                maxChange = Math.abs(policyValues.get(state) - previous.get(state));
                
                if(maxChange < delta) converge = true;
                
                
                
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		while(!converge);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		
	
	
	/**This method should be run AFTER the {@link PolicyIterationAgent#evaluatePolicy} train method to improve the current policy according to 
	 * {@link PolicyIterationAgent#policyValues}. You will need to do a single step of expectimax from each game (state) key in {@link PolicyIterationAgent#curPolicy} 
	 * to look for a move/action that potentially improves the current policy. 
	 * 
	 * @return true if the policy improved. Returns false if there was no improvement, i.e. the policy already returned the optimal actions.
	 */
	protected boolean improvePolicy()
	{
		boolean improve = false;
		double max = -1;
		Move move;
		double sum = 0;
		
		
		
		
		
		for(Game state:policyValues.keySet()) {
			if (state.isTerminal()) {
				policyValues.replace(state, 0.0);
				continue;
			}
			
			move = curPolicy.get(state);
			max = -1000;
			
			
			for (Move action : state.getPossibleMoves()) {
				
				sum = 0;
				
				
				for(TransitionProb probability:mdp.generateTransitions(state, action)) {
                	Outcome x = probability.outcome;
					double tempCalc = x.localReward + discount * policyValues.get(x.sPrime);
					double calculation = probability.prob * tempCalc;
					sum = sum + calculation;
                }
				
				
				if (max < sum) {
					max = sum;
					move = action;
				}
			
			}
			
			
			if(!move.equals(curPolicy.get(state))) {
				curPolicy.replace(state, move);
				improve = true;
				
				
			}
	
			
			
			
			
		}
		
		
		
		
		
		
		return improve;
	}
	
	/**
	 * The (convergence) delta
	 */
	double delta=0.1;
	
	/**
	 * This method should perform policy evaluation and policy improvement steps until convergence (i.e. until the policy
	 * no longer changes), and so uses your 
	 * {@link PolicyIterationAgent#evaluatePolicy} and {@link PolicyIterationAgent#improvePolicy} methods.
	 */
	public void train()
	{
		
		boolean trainingDone = true;
		
		do {
			evaluatePolicy(delta);
			trainingDone = improvePolicy();
		}
		while(trainingDone);
		
		
		super.policy = new Policy(curPolicy);
		
		
		
	}
	
	public static void main(String[] args) throws IllegalMoveException
	{
		/**
		 * Test code to run the Policy Iteration Agent agains a Human Agent.
		 */
		PolicyIterationAgent pi=new PolicyIterationAgent();
		
		HumanAgent h=new HumanAgent();
		
		Game g=new Game(pi, h, h);
		
		g.playOut();
		
		
	}
	

}
