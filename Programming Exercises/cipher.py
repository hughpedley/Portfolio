# This is for exercise 3, 'easy' difficulty from /r/dailyprogrammers (write an encoder and, if possible, decoder for an alphabetical cipher).
# Python already has an en/decoder for rot13 in its standard library, so this was pretty easy here.
# While this is short and works, I saw one implementation in C++ that only needs one line. That's bonkers.

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