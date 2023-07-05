package ticTacToe;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Value Iteration Agent, only very partially implemented. The methods to implement are: 
 * (1) {@link ValueIterationAgent2#iterate}
 * (2) {@link ValueIterationAgent2#extractPolicy}
 * 
 * You may also want/need to edit {@link ValueIterationAgent2#train} - feel free to do this, but you probably won't need to.
 * @author ae187
 *
 */
public class ValueIterationAgent2 extends Agent {

	/**
	 * This map is used to store the values of states
	 */
	Map<Game, Double> valueFunction=new HashMap<Game, Double>();
	
	/**
	 * the discount factor
	 */
	double discount=0.9;
	
	/**
	 * the MDP model
	 */
	TTTMDP mdp=new TTTMDP();
	
	/**
	 * the number of iterations to perform - feel free to change this/try out different numbers of iterations
	 */
	int k=10;
	
	
	/**
	 * This constructor trains the agent offline first and sets its policy
	 */
	public ValueIterationAgent2()
	{
		super();
		mdp=new TTTMDP();
		this.discount=0.9;
		initValues();
		train();
	}
	
	
	/**
	 * Use this constructor to initialise your agent with an existing policy
	 * @param p
	 */
	public ValueIterationAgent2(Policy p) {
		super(p);
		
	}

	public ValueIterationAgent2(double discountFactor) {
		
		this.discount=discountFactor;
		mdp=new TTTMDP();
		initValues();
		train();
	}
	
	/**
	 * Initialises the {@link ValueIterationAgent2#valueFunction} map, and sets the initial value of all states to 0 
	 * (V0 from the lectures). Uses {@link Game#inverseHash} and {@link Game#generateAllValidGames(char)} to do this. 
	 * 
	 */
	public void initValues()
	{
		
		List<Game> allGames=Game.generateAllValidGames('X');//all valid games where it is X's turn, or it's terminal.
		for(Game g: allGames)
			this.valueFunction.put(g, 0.0);
		
		
		
	}
	
	
	
	public ValueIterationAgent2(double discountFactor, double winReward, double loseReward, double livingReward, double drawReward)
	{
		this.discount=discountFactor;
		mdp=new TTTMDP(winReward, loseReward, livingReward, drawReward);
	}
	
	/**
	 
	
	/*
	 * Performs {@link #k} value iteration steps. After running this method, the {@link ValueIterationAgent2#valueFunction} map should contain
	 * the (current) values of each reachable state. You should use the {@link TTTMDP} provided to do this.
	 * 
	 *
	 */
	public void iterate()
	{
		
		/* YOUR CODE HERE
		 * 
		 * 
		 */
		
		
		// Run the value iteration loop k-times.
		for(int i = 0; i<k;i++) {
			// we loop over all the Game's (states) in the value hashmap
			for(Game g: valueFunction.keySet()) {
				// we initialise the max variable we use for every state here. It is started of with the value -1, so that when we later compare it
				// to the sum values, it is always less than it.
				double max = -10;
				
				//if g (the game state in this instance of the loop) is a terminal state, we replace it with a value of 0.00, and then progress to the next iteration of the loop.
				if(g.isTerminal()) {
					valueFunction.replace(g, 0.00);
					continue;
				}
				
				
				
				// for every move that is possible in this game state
				for(Move m:g.getPossibleMoves()) {
					// we initialise a double variable sum, to be used to sum over every calculation at eacj transition probability.
					double sum = 0;
					List<TransitionProb> tranList = mdp.generateTransitions(g, m);
					// we generate the a list that holds all the transitional probabilities using the function generateTransitions in the mdp object of type TTTMDP
					// we pass in the loop variables g and m (the current state and a possible move) into the function, to get the adequate output.		
					for(TransitionProb tran:tranList) {
						// for every transitional probability in the generated transitions, we do the following calculation (bellman equation).
						sum+= tran.prob*(tran.outcome.localReward+(discount*valueFunction.get(tran.outcome.sPrime)));
						
					}
					
					// if the value in sum is bigger then the value in max, then we set max equal to it.
					if(sum>max) {
						max = sum;
						
					}
				}
				
				// we replace the according value for this g value (state), inside the value list
				valueFunction.replace(g, max);
			}
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	/**This method should be run AFTER the train method to extract a policy according to {@link ValueIterationAgent2#valueFunction}
	 * You will need to do a single step of expectimax from each game (state) key in {@link ValueIterationAgent2#valueFunction} 
	 * to extract a policy.
	 * 
	 * @return the policy according to {@link ValueIterationAgent2#valueFunction}
	 */
	public Policy extractPolicy()
	{
		/*
		 * YOUR CODE HERE
		 */
		// create the policy object to hold the policy we extract.
		Policy polis = new Policy();
		
			
		// Like the iterate function, however, we keep track of the move that has the maximum value, and put it into the policy's move list
		// to effectively optimize.
			for(Game g: valueFunction.keySet()) {
				
			
				double max = -1;
				//Move maxMove = null;
				Move maxMove = null;
				for(Move m:g.getPossibleMoves()) {
					double sum = 0;
					List<TransitionProb> tranList = mdp.generateTransitions(g, m);
					for(TransitionProb tran:tranList) {
						sum+= tran.prob*(tran.outcome.localReward+(discount*valueFunction.get(tran.outcome.sPrime)));
						
					}
					if(sum>=max) {
						max = sum;
						maxMove = m;
					}
				}
				polis.policy.put(g, maxMove);
				
			}
			
		
		
			
		return polis;
	}
	
	/**
	 * This method solves the mdp using your implementation of {@link ValueIterationAgent2#extractPolicy} and
	 * {@link ValueIterationAgent2#iterate}. 
	 */
	public void train()
	{
		/**
		 * First run value iteration
		 */
		this.iterate();
		/**
		 * now extract policy from the values in {@link ValueIterationAgent#valueFunction} and set the agent's policy 
		 *  
		 */
		
		super.policy=extractPolicy();
		
		if (this.policy==null)
		{
			System.out.println("Unimplemented methods! First implement the iterate() & extractPolicy() methods");
			//System.exit(1);
		}
		
		
		
	}

	public static void main(String a[]) throws IllegalMoveException
	{
		//Test method to play the agent against a human agent.
		ValueIterationAgent2 agent=new ValueIterationAgent2();
		HumanAgent d=new HumanAgent();
		
		Game g=new Game(agent, d, d);
		g.playOut();
		
		
		

		
		
	}
}
