fname = input("Please enter file name: ")

f = open(fname, "r")

maxLength = 0

for word in f:
    word = word.strip()
    if len(word) > maxLength:
        maxLength = len(word)

print(f"Maximum word length is {maxLength}")
