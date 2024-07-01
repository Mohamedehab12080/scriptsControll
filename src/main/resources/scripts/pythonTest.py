# countdown.py

import time
import sys

countdown_from = 5

for i in range(countdown_from, 0, -1):
    print(f"Countdown: {i}")
    sys.stdout.flush()  # Flush the output buffer
    time.sleep(1)

print("Countdown completed!")
sys.stdout.flush()  # Flush the output buffer
