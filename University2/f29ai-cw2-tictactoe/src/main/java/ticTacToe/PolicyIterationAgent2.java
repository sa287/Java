package ticTacToe;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A policy iteration agent. You should implement the following methods: (1)
 * {@link PolicyIterationAgent2#evaluatePolicy}: this is the policy evaluation
 * step from your lectures (2) {@link PolicyIterationAgent2#improvePolicy}: this
 * is the policy improvement step from your lectures (3)
 * {@link PolicyIterationAgent2#train}: this is a method that should
 * runs/alternate (1) and (2) until convergence.
 * 
 * NOTE: there are two types of convergence involved in Policy Iteration:
 * Convergence of the Values of the current policy, and Convergence of the
 * current policy to the optimal policy. The former happens when the values of
 * the current policy no longer improve by much (i.e. the maximum improvement is
 * less than some small delta). The latter happens when the policy improvement
 * step no longer updates the policy, i.e. the current policy is already
 * optimal. The algorithm should stop when this happens.
 * 
 * @author ae187
 *
 */
public class PolicyIterationAgent2 extends Agent {

	/**
	 * This map is used to store the values of states according to the current
	 * policy (policy evaluation).
	 */
	HashMap<Game, Double> policyValues = new HashMap<Game, Double>();

	/**
	 * This stores the current policy as a map from {@link Game}s to {@link Move}.
	 */
	HashMap<Game, Move> curPolicy = new HashMap<Game, Move>();

	double discount = 0.9;

	/**
	 * The mdp model used, see {@link TTTMDP}
	 */
	TTTMDP mdp;

	/**
	 * loads the policy from file if one exists. Policies should be stored in .pol
	 * files directly under the project folder.
	 */
	public PolicyIterationAgent2() {
		super();
		this.mdp = new TTTMDP();
		initValues();
		initRandomPolicy();
		train();

	}

	/**
	 * Use this constructor to initialise your agent with an existing policy
	 * 
	 * @param p
	 */
	public PolicyIterationAgent2(Policy p) {
		super(p);

	}

	/**
	 * Use this constructor to initialise a learning agent with default MDP
	 * paramters (rewards, transitions, etc) as specified in {@link TTTMDP}
	 * 
	 * @param discountFactor
	 */
	public PolicyIterationAgent2(double discountFactor) {

		this.discount = discountFactor;
		this.mdp = new TTTMDP();
		initValues();
		initRandomPolicy();
		train();
	}

	/**
	 * Use this constructor to set the various parameters of the Tic-Tac-Toe MDP
	 * 
	 * @param discountFactor
	 * @param winningReward
	 * @param losingReward
	 * @param livingReward
	 * @param drawReward
	 */
	public PolicyIterationAgent2(double discountFactor, double winningReward, double losingReward, double livingReward,
			double drawReward) {
		this.discount = discountFactor;
		this.mdp = new TTTMDP(winningReward, losingReward, livingReward, drawReward);
		initValues();
		initRandomPolicy();
		train();
	}

	/**
	 * Initialises the {@link #policyValues} map, and sets the initial value of all
	 * states to 0 (V0 under some policy pi ({@link #curPolicy} from the lectures).
	 * Uses {@link Game#inverseHash} and {@link Game#generateAllValidGames(char)} to
	 * do this.
	 * 
	 */
	public void initValues() {
		List<Game> allGames = Game.generateAllValidGames('X');// all valid games where it is X's turn, or it's terminal.
		for (Game g : allGames)
			this.policyValues.put(g, 0.0);

	}

	/**
	 * You should implement this method to initially generate a random policy, i.e.
	 * fill the {@link #curPolicy} for every state. Take care that the moves you
	 * choose for each state ARE VALID. You can use the
	 * {@link Game#getPossibleMoves()} method to get a list of valid moves and
	 * choose randomly between them.
	 */
	public void initRandomPolicy() {
		/*
		 * YOUR CODE HERE
		 */
		
		// we use this function to initialise the policyValues list, and the curPolicy list with moves acquired randomly.
		
		// we create a random number generator using the random class
		Random rand = new Random();

		// for every state in the policyValue set
		for (Game g : policyValues.keySet()) {
			
			// if any state is a terminal one, we replace its value in the policyValue set with 0.00, and continue to the next iteration, just like previously in VI
			if (g.isTerminal()) {
				policyValues.replace(g, 0.00);
				continue;
				
			}
			
			// we create a Move object that holds a randomly assigned Move, from a list of moves that is possible in this game state
			Move randomMove = g.getPossibleMoves().get(rand.nextInt(g.getPossibleMoves().size()));

			// we put this random Move object, in with the game state
			curPolicy.put(g, randomMove);
			
		}
	}

	/**
	 * Performs policy evaluation steps until the maximum change in values is less
	 * than {@code delta}, in other words until the values under the currrent policy
	 * converge. After running this method, the
	 * {@link PolicyIterationAgent2#policyValues} map should contain the values of
	 * each reachable state under the current policy. You should use the
	 * {@link TTTMDP} {@link PolicyIterationAgent2#mdp} provided to do this.
	 *
	 * @param delta
	 */
protected void evaluatePolicy(double delta) {
        
       
		// create the boolean, loop, that we use to keep our while loop running for its intended duration
        boolean loop = true;
        
        // create a copy of the policyValues list, before it is updated
        HashMap<Game, Double> x = policyValues;
        
        // initialise the double variables, to be used in our calculations, maxUpdate holds the biggest change in that iteration, bigMax holds the biggest change in the whole loop
        // whereas oldVal and newVal are used to hold the values for the convergence calculation newVal - oldVal.
        double maxUpdate = 10;
        double oldVal = 0;
        double newVal = 0;
        double bigMax = 0;
        	
        // while the boolean loop, is true, it will run this loop
        while (loop) {
        	
        	// This for loop starts off the same as the previous ones
            for (Game g : policyValues.keySet()) {

                if (g.isTerminal()) {
                    policyValues.replace(g, 0.00);
                    continue;
                }
                // we initialise our sum variable here
                double sum = 0;
                
                // we get the current Move object, that corresponds to the current game state
                Move m = curPolicy.get(g);
                	
                // we do the bellman equations like previous
                    List<TransitionProb> tranList = mdp.generateTransitions(g, m);
                    for (TransitionProb tran : tranList) {
                        sum = sum + (tran.prob
                                * (tran.outcome.localReward + (discount * policyValues.get(tran.outcome.sPrime))));

                    }

                
                // we replace the value in the policyValues hashmap that corresponds to the current state, with the variable sum (result of bellman equation)
                policyValues.replace(g, sum);

                // newVal is set to the current game state's value (the one we just updated)
                newVal = policyValues.get(g);
                // oldVal is set to the same game state's previous value
                oldVal = x.get(g);
                // we calculate the maximum change between these values, as if they are less than delta, it means we have converged and should end the loo[
                maxUpdate = Math.abs(newVal - oldVal);
                
                // we use this if block to update the bigMax variable, to ensure we are getting the correct value for the maximum change in values, at every iteration.
                if (maxUpdate > bigMax) {
                    bigMax = maxUpdate;

                }
                
                // if we have converged, then we set the boolean loop to false, and end the function in turn
                if (bigMax < delta) {
                    loop = false;
                }

            }

        }

    }



	/**
	 * This method should be run AFTER the
	 * {@link PolicyIterationAgent2#evaluatePolicy} train method to improve the
	 * current policy according to {@link PolicyIterationAgent2#policyValues}. You
	 * will need to do a single step of expectimax from each game (state) key in
	 * {@link PolicyIterationAgent2#curPolicy} to look for a move/action that
	 * potentially improves the current policy.
	 * 
	 * @return true if the policy improved. Returns false if there was no
	 *         improvement, i.e. the policy already returned the optimal actions.
	 */
	protected boolean improvePolicy() {
		/* YOUR CODE HERE */
		
		
		
		// we create the improve boolean variable that is used to verify if any values have been improved during this run
		boolean improve = false;
		
		// we start of like previous functions, (think the policy extraction function in VI)
		for (Game g : policyValues.keySet()) {
			double max = -1;
			
			Move maxMove = curPolicy.get(g);
			
			if (g.isTerminal()) {
				policyValues.replace(g, 0.00);
				continue;
			}
			for (Move m : g.getPossibleMoves()) {
				
				double sum = 0;
				List<TransitionProb> tranList = mdp.generateTransitions(g, m);
				for (TransitionProb tran : tranList) {
					sum = sum+(tran.prob * (tran.outcome.localReward + (discount * policyValues.get(tran.outcome.sPrime))));

				}
				if (sum >= max) {
					max = sum;
					maxMove = m;
									}
			}
			
			
			// if the maxMove (move with the highest corresponding value) is not in the curPolicy move list with the current game state, this means that
			// the policy can be improved
			if (!maxMove.equals(curPolicy.get(g))) {
				
				// so we replace the existing contents with the maxMove we have just found
				curPolicy.replace(g,maxMove);
				
				// as the policy has been improved, we set improve to true
				improve = true;
				

			}
			

		}
		
		
		return improve;
	}

	/**
	 * The (convergence) delta
	 */
	double delta = 0.1;

	/**
	 * This method should perform policy evaluation and policy improvement steps
	 * until convergence (i.e. until the policy no longer changes), and so uses your
	 * {@link PolicyIterationAgent2#evaluatePolicy} and
	 * {@link PolicyIterationAgent2#improvePolicy} methods.
	 */
	public void train() {
		/* YOUR CODE HERE */
	
		// the main loop used for "training" our policies with the two steps evalute and improve policy.
		
		
		// we use a similar set up to the previous function, where change acts as the linchpin for the loop
		// it starts off as true to initialise the while loop.
		boolean change = true;
		while (change) {
			
			
			// we start of by evaluating our policy using  the evaluatePolicy() method, making sure to pass in the required delta value
			evaluatePolicy(delta);	
			
			// as improvePolicy() returns a boolean, we set change equal to it. If no change (no improvement) has occurred, we exit the while loop
			// as no more work needs to be done.
			change = improvePolicy();
		
			
		}
		
		// we finally make sure that the curPolicy policy object is initialised
		super.policy = new Policy(curPolicy);
		
		

	}

	public static void main(String[] args) throws IllegalMoveException {
		/**
		 * Test code to run the Policy Iteration Agent agains a Human Agent.
		 */
		PolicyIterationAgent2 pi = new PolicyIterationAgent2();

		HumanAgent h = new HumanAgent();

		Game g = new Game(pi, h, h);

		g.playOut();

	}

}
