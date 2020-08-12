# The Cable Car to the Summit of Erebor
*Concurrency Theory*

**Background**

Long after the One Ring had been destroyed in the fires of Mount Doom, the dwarfs decided to return to their beloved
mountain Erebor, also known as the Lonely Mountain. They decided to turn it into a tourist attraction and to build a
cable car to the top of the mountain.
The cable car makes trips to the top of the mountain and back down again on a regular basis until it has transported
500 people up the mountain and back down again. Then it ceases operation for the day. On each trip it can take a
maximum of 10 people to the summit, and on each return trip a maximum of 10 people back to the foot of the mountain.
Since the dwarfs are very concerned about preserving Erebor, no more than 50 people are allowed on the summit at each
point in time.
Tourists arrive randomly at the foot of the mountain during the day to use the cable car to get to the summit. They
might have to wait for a space in the cable car because of its limited capacity. Also, the cable car only takes as many
people to the summit as the limit of 50 people allows. Once a tourist has travelled to the top of the mountain, he or she
stays there for a while and then waits for a cable car to return to the foot of Erebor.

**Create**

One thread for tourists who want to get to the top of the mountain. 
<br>Another thread for tourists who want to get down again.

**Situations**

This method acquires the given number of permits from the semaphore if all become available within the given waiting time and the current thread has not been interrupted. If insufficient permits are available then the current thread becomes dormant until one of three things happens:
<br>• Some other thread invokes the release methods for this semaphore.
<br>• Some other thread interrupts the current thread.
<br>• The specified waiting time elapses.

**Completion**

Print out such as:
<br>• A tourist arrives at the base station of the cable car.
<br>• The cable car leaves with 7 passengers to the summit of the mountain.
<br>• A tourist decides to leave the mountain and goes to the summit station.
<br>• The cable car leaves with 4 passengers to the foot of the moutain
