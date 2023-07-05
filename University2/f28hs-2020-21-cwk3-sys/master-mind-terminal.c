/*
 * MasterMind: a cut down version with just the master-mind game logic (purely C) and no external devices

Sample run:
Contents of the sequence (of length 3):  2 1 1
Input seq (len 3): 1 2 3
0 2
Input seq (len 3): 3 2 1
1 1
Input seq (len 3): 2 1 1
3 0
SUCCESS after 3 iterations; secret sequence is  2 1 1

 * Compile:    gcc -o cw3  master-mind-terminal.c
 * Run:        ./cw3

 */

/* --------------------------------------------------------------------------- */

/* Library headers from libc functions */
#include <stdio.h>
#include <stdarg.h>
#include <stdint.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <time.h>

/* Constants */
#define COL 3
#define LEN 3

/* Global variables */

static const int colors = COL;
static const int seqlen = LEN;


/* variables for the program, theSeq refers to the main sequence , app match to appropriate matches and exMatch to the exact, guessM is a backup int that is used 
in some parts of the code, particularly for the countMatches function to hold the guess data in the right format*/
static int *theSeq = 0;
static int appMatch = 0;
static int exMatch = 0;
static int guessM = 0;

/* Aux functions */

/* initialise the secret sequence; by default it should be a random sequence, with -s use that sequence */
void initSeq()
{
  /* COMPLETE the code here */
  /* create char and int variables to hold the sequence data in both forms. To make each digit of the 3 digit number random i seperated each int and made a temp
  holding char array to hold the newly calculated random values, these values are then put into each one, two and three int variable, which are then turned into strings
  and put into the corresponding S char array. They are then concatenated and converted into int, to then be put into our theSeq variable using atoi*/
  char temp2[4];
  int one;
  int two;
  int three;

  char oneS[4];
  char twoS[4];
  char threeS[4];

  /**/
  time_t randomInit;
  srand((unsigned)time(&randomInit));

  for (int i = 0; i < seqlen; i++)
  {
    int temp = (rand() % 3) + 1;
    temp2[i] = temp;
  }
  one = temp2[1];
  two = temp2[2];
  three = temp2[3];

  sprintf(oneS, "%d", one);
  sprintf(twoS, "%d", two);
  sprintf(threeS, "%d", three);

  strcat(oneS, twoS);
  strcat(oneS, threeS);

  theSeq = atoi(oneS);
}

/* display the sequence on the terminal window, using the format from the sample run above */
void showSeq(int *seq)
{
  /* COMPLETE the code here */
  fprintf(stdout, "Contents of the sequence (of length 3) : %d\n", seq);
}
/* counts how many entries in seq2 match entries in seq1 */
/* returns exact and approximate matches  */
int countMatches(int *seq1, int *seq2)
{
  /* COMPLETE the code here */
  int temp1 = seq1;
  int temp2 = seq2;
  //fprintf(stdout,"%d",&temp2);
  char seqString1[5];
  char seqString2[5];
  itoa(temp1, seqString1, 10);
  itoa(temp2, seqString2, 10);
  appMatch = 0;
  exMatch = 0;
  //fprintf(stdout,"%s",seqString1);
  //fprintf(stdout,"%s",seqString2);
  //fprintf(stdout,"%c",seqString1[0]);
  //fprintf(stdout,"%c",seqString2[0]);
  for (int i = 0; i < seqlen; i++)
  {

    if (i == 0)
    {
      if (seqString2[i] == seqString1[i])
      {
        exMatch++;
      }
      else if (seqString2[i] == seqString1[i + 1])
      {
        appMatch++;
      }
      else if (seqString2[i] == seqString1[i + 2])
      {
        appMatch++;
      }
    }
    else if (i == 1)
    {
      if (seqString2[i] == seqString1[i])
      {

        exMatch++;
      }
      else if (seqString2[i] == seqString1[i - 1])
      {
        appMatch++;
      }
      else if (seqString2[i] == seqString1[i + 1])
      {
        appMatch++;
      }
    }
    else if (i == 2)
    {
      if (seqString2[i] == seqString1[i])
      {

        exMatch++;
      }
      else if (seqString2[i] == seqString1[i - 2])
      {

        appMatch++;
      }
      else if (seqString2[i] == seqString1[i - 1])
      {
        appMatch++;
      }
    }
  }
}

/* show the results from calling countMatches on seq1 and seq1 */
void showMatches(int *seq1, int *seq2)
{
  /* COMPLETE the code here */
  fprintf(stdout, "exact match: %d \nappropriate match: %d\n", seq1, seq2);
}

/* read a guess sequence fron stdin and store the values in arr */
void readString(int *arr)
{
  /* COMPLETE the code here */

  printf("Please enter your sequence in\n");
  scanf("%d", &arr);
  // printf("%d",arr);
  guessM = arr;
  //printf("%d",guessM);
}

/* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

int main(int argc, char **argv)
{
  /* DEFINE your variables here */
  int found = 0, attempts = 0;
  /* for getopts() command line processing */
  int verbose = 0, help = 0, unittest = 0, debug = 0;
  char *sseq = NULL;
  int *guess = 0;

  // see: man 3 getopt for docu and an example of command line parsing
  // Use this template to process command line options and to store the input
  {
    int opt;
    while ((opt = getopt(argc, argv, "vuds:")) != -1)
    {
      switch (opt)
      {
      case 'v':
        verbose = 1;
        break;
      case 'u':
        unittest = 1;
        break;
      case 'd':
        debug = 1;
        break;
      case 's':
        sseq = (char *)malloc(LEN * sizeof(char));
        strcpy(sseq, optarg);
        break;
      default: /* '?' */
        fprintf(stderr, "Usage: %s [-v] [-d] [-s] <secret sequence> [-u] <secret sequence> <guess sequence> \n", argv[0]);
        exit(EXIT_FAILURE);
      }
    }
    if (unittest && optind >= argc)
    {
      fprintf(stderr, "Expected argument after options\n");
      exit(EXIT_FAILURE);
    }

    if (verbose && unittest)
    {
      printf("1st argument = %s\n", argv[optind]);
      printf("2nd argument = %s\n", argv[optind + 1]);
    }
  }

  if (verbose)
  {
    fprintf(stdout, "Settings for running the program\n");
    fprintf(stdout, "Verbose is %s\n", (verbose ? "ON" : "OFF"));
    fprintf(stdout, "Debug is %s\n", (debug ? "ON" : "OFF"));
    fprintf(stdout, "Unittest is %s\n", (unittest ? "ON" : "OFF"));
    if (sseq)
      fprintf(stdout, "Secret sequence set to %s\n", sseq);
  }

  if (sseq)
  { // explicitly setting secret sequence
    /* SET the secret sequence here */
    //fprintf(stdout,"%s",sseq);
    int i;
    sscanf(sseq, "%d", &theSeq);
    showSeq(theSeq);
  }
  if (unittest)
  {
    /* SET secret and guess sequence here */
    /* then run the countMatches function and show the result */
    sscanf(argv[optind], "%d", &theSeq);
    sscanf(argv[optind + 1], "%d", &guess);

    //printf("%s",theSeq);
    countMatches(theSeq, guess);
    //fprintf(stdout,"%d %d", appMatch,exMatch);
    printf("%i exact matches\n%i approximate matches\n", exMatch, appMatch);

    /* for now just terminate; DELETE these two lines, once you have an implementation for -u in here */
    //fprintf(stdout, "INCOMPLETE implementation, terminating program\n");
    return EXIT_SUCCESS;
  }

  // -----------------------------------------------------------------------------
  if (theSeq == 0)
  {
    initSeq();
  }
  if(debug == 1){
    showSeq(theSeq);
  }
  // +++++ main loop
  while (!found)
  {
    attempts++;
    /* IMPLEMENT the main game logic here */

    readString(guess);

    countMatches(theSeq, guessM);
    showMatches(exMatch, appMatch);
    if (exMatch == 3)
    {
      found = 1;
    }
    if(attempts ==3 ){
      break;
    }
  }

  if (found)
  {
    /* print SUCCESS and the number of iterations */
    printf("Success!\n");
    printf("Times tried: %d\n", attempts);
  }
  else
  {
    /* print something else */
    printf("Unlucky, you have ran out of tries\n");
  }
  return EXIT_SUCCESS;
}
