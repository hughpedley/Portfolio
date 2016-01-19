#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

int getNumArgs(char*);

char *delimiters = " ()<>|&;\n\t";

int main()
{
	char **args;
	char *arg;
	char input[512];
	char token[512];
	int processID = 0;
	int numArgs = 0;
	int status;
	int count = 0;

	while(1)
	{
		printf("hcp13's shell> ");

		//Get user input
		fgets(input, 512, stdin);
		numArgs = getNumArgs(input);
		args = malloc(sizeof(char*) * (numArgs + 1));

		//copy arguments
		strcpy(token, input);
		arg = strtok(token, delimiters);
		count = 0;
		while(arg != NULL)
		{
			args[count] = (char*) malloc(sizeof(char) * strlen(arg));
			strcpy(args[count], arg);
			arg = strtok(NULL, delimiters);
			count++;
		}

		//If user entered nothing, keep looping
		args[numArgs] = NULL;
		if(args[0] == NULL)
		{
			continue;
		}

		//if user entered exit, exits out of shell
		if(strcmp(args[0], "exit") == 0)
		{
			exit(0);
		}
		//change directory
		else if(strcmp(args[0], "cd") == 0)
		{
			//Changes directory within if statement, if returns an error
			//prints out a message
			if(chdir(args[1]) == -1)
			{
				if(args[1] == NULL)
				{
					printf("cd: : Please specify a directory.\n");
				}
				else{
					printf("cd: %s: No such file or directory.\n", args[1]);
				}
			}
		}
		else
		{
			//Make a fork and sleep one of the forks, the other continues until exiting
			processID = fork();
			if(processID != 0)
			{
				wait(&status);
			}
			else
			{
				//handles rest of UNIX inputs
				if(strstr(input, ">>") != NULL)
				{
					freopen(args[numArgs - 1], "a", stdout);
					free(args[numArgs - 1]);
					args[numArgs - 1] = NULL;
				}
				else if(strstr(input, ">") != NULL)
				{
					freopen(args[numArgs - 1], "w", stdout);
					free(args[numArgs - 1]);
					args[numArgs - 1] = NULL;
				}
				else if(strstr(input, "<") != NULL)
				{
					freopen(args[numArgs - 1], "r", stdin);
					free(args[numArgs - 1]);
					args[numArgs - 1] = NULL;
				}

				execvp(args[0], args);
				exit(0);
			}
		}
	}

	return 0;
}

//Get number of arguments user entered
int getNumArgs(char* input)
{
	char arguments[512];
	char* current;
	int count = 0;
	strcpy(arguments, input);
	current = strtok(arguments, delimiters);

	while(current != NULL)
	{
		count++;
		current = strtok(NULL, delimiters);
	}

	return count;
}
