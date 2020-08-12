import os
import seaborn as sns
import pandas as pd
import numpy as np
import re
import matplotlib.pyplot as plt

directory = "./"

times = []


for root, dirs, files in os.walk(directory):
    for file in files:
        try:
            if file.endswith(".csv"):
                print(file)
                f = open(file, 'r')
                times.append(f.readline())
                f.close()
        except:
            continue

order = []
size = []
algorithm = []
time = []
for lines in times:
    matches = re.findall(
        r'Order: (\w+)\sSize:(\d+)\sAlgo:(\w+)\s+Time\s+\(ms\):\s(\d+)', lines)
    for match in matches:
        order.append(match[0])
        size .append(int(match[1]))
        algorithm.append(match[2])
        time.append(match[3])

df = pd.DataFrame({"Order": order, "Size": size,
                   "Algorithm": algorithm, "Time": time})

print(df)

fig, (bubble_ax, insertion_ax), (merge_ax,
                                 quick_ax) = plt.subplot(nrows=2, ncols=2)
