# This is the 'hard' version of exercise 1 on reddit's dailyprogrammer
# subreddit. While this particular exercise is not actually very hard, it
# is still good practice to brush up on python skills.
# Description from the post: we all know the classic "guessing game" with higher
# or lower prompts. lets do a role reversal; you create a program that will guess 
# numbers between 1-100, and respond appropriately based on whether users say that
# the number is too high or too low. Try to make a program that can guess your
# number based on user input and great code!

highest = 101
lowest = 1
guesses = 0
user_reply = ""

print "Let's play a guessing game. Pick a number between 1 and 100, and I'll see how long it takes to guess it."

while user_reply != "yes":
    guess = int((highest + lowest)/2)
    guesses += 1
    print "Is your number %d? Please answer yes or no." %guess
    user_reply = raw_input()
    if user_reply == "yes":
        print "Yay! I guessed your number in %d guesses." %guesses
    else:
        print "Is your number higher or lower than %d? Please type higher or lower." %guess
        difference = raw_input()
        if difference == "higher":
            lowest = guess
        elif difference == "lower":
            highest = guess
        else:
            print "You did not enter 'higher' or 'lower'."