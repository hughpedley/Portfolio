# Copied and pasted from the 'assignment' on the dailyprogrammer subreddit:
# Hello, coders! An important part of programming is being able to apply your programs, so your challenge for today is to create a calculator application that has use in your life. It might be an interest calculator, or it might be something that you can use in the classroom. For example, if you were in physics class, you might want to make a F = M * A calc.
# EXTRA CREDIT: make the calculator have multiple functions! Not only should it be able to calculate F = M * A, but also A = F/M, and M = F/A!

def force(m, a):
    return m * a

def acceleration(f, m):
    return f / m

def mass(f, a):
    return f / a

print "Select a formula to use:"
print "1) Calculate force"
print "2) Calculate acceleration"
print "3) Calculate mass"

selection = raw_input()
if selection == "1":
    print "Enter mass:"
    m = float(raw_input())
    print "Enter acceleration:"
    a = float(raw_input())
    f = force(m, a)
    print "The force is %d newtons." %f
elif selection == "2":
    print "Enter force:"
    f = float(raw_input())
    print "Enter mass:"
    m = float(raw_input())
    a = acceleration(f, m)
    print "The acceleration is %d m/s^2." %a
elif selection == "3":
    print "Enter force:"
    f = float(raw_input())
    print "Enter acceleration:"
    a = float(raw_input())
    m = mass(f, a)
    print "The mass is %d kg." %m
else:
    print "You did not input a valid option."