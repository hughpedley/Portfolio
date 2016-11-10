# Search through the provided unscrambled words and compare them to the scrambled words given separately.
# Identify which scrambled words match the much larger list of unscrambled words!
from urllib import urlopen

# Open list of words and separate them in all_words array
all_words = urlopen('http://pastebin.com/raw.php?i=jSD873gL').read().split() 

# already given scrambled words
scrambled_words = ['mkeart', 'sleewa', 'edcudls', 'iragoge', 'usrlsle',
                   'nalraoci', 'nsdeuto', 'amrhat', 'inknsy', 'iferkna']

# Sort scrambled words by length, then compare them to any words from the total list
for scrambled_words in sorted(scrambled_words, key=len):
    matches = [word for word in all_words if sorted(scrambled_words) == sorted(word)]
    print scrambled_words + ": " + ' '.join(matches)