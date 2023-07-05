# ReentrantLock Properties
### What will you learn?
More about how Java locks work, what info. you can get on them at run-time, and how you can cause a particular run-time error that is not picked up at compile-time.

### Why do this exercise?
1. It is examinable.
2. It will help you debug coursework that uses Extrinsic Monitors.

### Instructions

1. Fork this project 
2. Clone and run the code. Check that you get Listing 1.
2. Read through the code and identify (a) what the LockThread class does, and (b) how many threads attempt to lock and/or unlock the lock.
3. Make sure you understand what each field is in the `LOCK STATUS` printout.
4. Change the number of LockThread instances generated to 0, run, and make sure you understand what has changed in the output and why.
6. Commit and Push with your answer in the comment.
5. Change the number of LockThread instances generated to 4, run repeatedly until something changes in the console output, and make sure you understand what has changed in the output and why.
6. Commit and Push with your answer in the comment.
7. Now uncomment: `lock.unlock();` in the `main` thread. Run - do you understand why there is a run-time error? Do you understand why this cannot be detected at compile time?
6. Commit and Push with your answer in the comment.


### Listing 1
```
main: running
Thread-0: calling lock.lock()
Thread-0: returned from lock.lock()


LOCK STATUS:

main: lock.isLocked() = true
main: lock.owner() = Thread-0
main: lock.hasQueuedThreads() = false
main: lock.getQueueLength() = 0
main: lock.getQueuedThreads() names = 

END LOCK STATUS
```