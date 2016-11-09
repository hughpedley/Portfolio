print "Enter 'e' to encode using rot13 cipher, or 'd' to decode using rot13 cipher."
user_input = raw_input()
if user_input == "e":
    print "Enter your word or phrase to encode:"
    print raw_input().encode('rot13')
elif user_input == "d":
    print "Enter your word or phrase to decode:"
    print raw_input().decode('rot13')
else:
    print "You did not enter a valid option. Please enter e or d."