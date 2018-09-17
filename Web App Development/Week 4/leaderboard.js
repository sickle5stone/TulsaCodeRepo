/*
    Lab 3 - Climbing the Leaderboard

    It's job interview time and you're being interviewed in JavaScript. You've been asked to solve
    the following algorithmic problem. Fortunately for you, in your third week of Web Apps
    Development, your instructor gave you a detailed overview of the JavaScript language. Armed
    with a sharp wit and your instructor's high hopes, you're more than ready to tackle this
    challenge.

    This is a problem from HackerRank with a medium difficulty and only a 58% success rate. This is
    exactly like the type of problem you would see in a coding interview --- it's actually about as
    difficult as most problems that a Freshmen, a Sophomore, or a Junior would be given in their
    coding internship interview.

    Description:

        Alice is playing an arcade game and wants to climb to the top of the leaderboard and wants
    to track her ranking. The game uses Dense Ranking, so its leaderboard works like this:

            (*) The player with the highest score is ranked number 1 on the leaderboard.
            (*) Players who have equal scores receive the same ranking number, and the next
            player(s) receive the immediately following ranking number.

        For example, the four players on the leaderboard have high scores of 100, 90, 90, and 80.
    Those players will have ranks 1, 2, 2, and 3, respectively. If Alice's scores are 70, 80 and
    105, her rankings after each game are 4, 3 and 1.

    We're now going to learn JavaScript by walking through the solution to this problem together.
    Please follow the instructions provided in comments. Part 1 will invole solving the challenge
    stated above while Part 2 will involve applying the more advanced JavaScript concepts that
    we learned in class. Part 3 consists of a few questions. Please log your answers.

    To run this script, download and install NodeJS, then execute the script with:
        $> node leaderboard.js

    Please submit this script to Harvey upon completion.

    P.S. If comments are indented please include them in the body of the if, for, or function
    to which they belong.

*/

// Part 1 - Climbing the Leaderboard

// Initialize a variable n to 7, the number of scores on the leaderboard.
// Don't forget to use semicolons.

var n = 7;

// Initialize a variable scores which is an Array with elements: 100, 100, 50, 40, 40, 20, 10 .
// Notice that the scores are in decreasing order.

var scores = [100,100,50,40,20,10];

// Initialize a variable m to 4, the number of games that Alice has played.

var m = 4;

// Initialize a variable games which is an Array with elements: 5, 25, 50, 120 .
// Notice that the scores are in increasing order.

var games = [5,25,50,120];

// Since we are using Dense Ranking, duplicates don't matter and should be filtered out.
// We will now implement a filter using iteration.

// Initialize a variable uniq which is an empty Object (a.k.a map or dictionary).

var uniq = {};

// Initialize a variable ranks which is an empty Array.

var ranks = [];

// Using a for loop, but not a for-of loop, iterate over the values of scores.

for (var i=0;i<scores.length;i++){
  // For each value scores[i] in scores, use an if statement to check if uniq contains scores[i].
  // If scores[i] is not in uniq,
  if (!(scores[i] in uniq)){
    // then push scores[i] to ranks and put scores[i] in uniq set to true .
    uniq[scores[i]] = true;
    ranks.push(scores[i]);
  }

  // end if

  // Otherwise, ignore scores[i] --- it's a duplicate.
}
// end for


// Log ranks so that we can see the filtered scores.
console.log(ranks)
// The output should be [ 100, 50, 40, 20, 10 ] .

// Define a function binarySearch which takes four parameters: rankings, aliceScore, i, j .
// This function will perform a binary search on rankings to determine where to insert aliceScore.

function binarySearch(rankings,aliceScore,i,j){
  
    // I won't have you validate the inputs, but when writing JavaScript: VALIDATE EVERYTHING!

    // We need a base case for binarySearch.
    // Use an if statement to check if i = j. Use proper equality.
    // If i = j, then compare rankings[i] to aliceScore.
    if (i===j){
        // If aliceScore >= rankings[i],
            // then return i --- aliceScore is the new i rank.


        // Else if aliceScore < rankings[i], // Use else here instead

            // then return i+1 --- aliceScore is the new i+1 rank.

        // end if

        // If you want style points, implement the return statement with a ternary operator.
        return (aliceScore >= rankings[i] ? i : i+1);
        // return (aliceScore >= rankings[i] ? i : i+1);
    }else if (i > j){
      return i;
    }
    // Else if i > j, // do not use else

        // then return i --- aliceScore is the new bottom rank

    // end if


    // Initialize a variable pivot with value (number of rankings) / 2 . Don't forget to use var.
    // Remember that JavaScript doesn't have integer division.
    // How can we make (number of rankings) / 2 an integer?
    // HINT: There is a Math library with a ceil function or you could use modulus.
    // With modulus, N % 2 = 1 means that N is odd. 0 means even. Don't forget operator precedence.
    // This is a recursive call so the number of rankings is the number of elements in slice (i,j)
    // (number of rankings) = (j - i)
    // Don't forget to offset by i so pivot = i + (number of rankings) / 2 .
    var pivot = i + ( (j - i) + (j - i) % 2 ) / 2;
    pivot = Math.ceil(pivot);

    // Use an if statement to check if rankings[pivot] = aliceScore.
    // If aliceScore = rankings[pivot],
        // then use a return statement to return pivot.
    // Else if aliceScore > rankings[pivot],
        // then return the recursive call of binarySearch on the front half of rankings.
        // rankings is descending so use arguments: rankings, aliceScore, i, pivot-1 .
    // Else if aliceScore < rankings[pivot],  // Use else here instead.
        // then return the recursive call of binarySearch on the back half of rankings.
        // rankings is descending so use arguments: rankings, aliceScore, pivot+1, j .

    // if (aliceScore == rankings[pivot]){
    //   return pivot
    // } else if (aliceScore > rankings[pivot]){
    //   return binarySearch(rankings,aliceScore,i,pivot-1)
    // } else {
    //   return binarySearch(rankings,aliceScore,pivot-1,j)
    // }
    return (aliceScore == rankings[pivot] ? pivot : (aliceScore > rankings[pivot] ? binarySearch(rankings,aliceScore,i,pivot-1) : binarySearch(rankings,aliceScore,pivot+1,j)))
    // end if

}
// end binarySearch.


// Using a for-of loop with variable gamej, iterate over the values of games.
for (var gamej of games){

    // For each value gamej, call binarySearch with arguments: ranks, gamej, 0, ranks.length-1 .
    // binarySearch will return an index, initialize a variable r equal to this index.
    // r is the rank assigned to gamej.
    var r = binarySearch(ranks,gamej,0,ranks.length-1);
    // If gamej is not in uniq,
    if (!(gamej in uniq)){
        // then splice gamej into ranks at index r and put gamej in uniq set to true .
        ranks.splice(r, 0, gamej);
        ranks[r] = gamej;
        uniq[gamej] = true;
    }
    // end if

    // Log the value of r (don't forget to add 1 to r since leaderboards are 1-indexed).
    console.log(r+1)

    // The output should be 6, 4, 2, 1 .
}
// end for-in


// Log ranks so that we can see the new leaderboard.
console.log(ranks)
// The output should be [ 120, 100, 50, 40, 25, 20, 10, 5 ] .


// Congratulations! You've just solved Climbing the Leaderboard.

// Part 2 - Callbacks and Async

// We will now look at another solution to Climbing the Leaderboard, one that uses callbacks.

// Set the value of n to 6 .

n = 6;

// Set the value of scores to an Array with elements: 100, 90, 90, 80, 75, 60 .

scores = [100,90,90,80,75,60];

// Set the value of m to 5 .

m = 5;

// Set the value of games to an Array with elements: 50, 65, 77, 90, 102 .

games = [50,65,77,90,102];

// Set uniq to an empty Object and set ranks to an empty Array.

uniq = {}
ranks = []

// Every Array provides a forEach method which can be called using: array.forEach([func]);.
// The forEach method calls the argument func once for each element in the array.
// Use the forEach method to iterate over scores.
// The argument that you pass should be an anonymous function which takes one parameter: scorei .
scores.forEach((scorei) => {
    // Inside the body of the anonymous function, call setTimeout(cb, 0);.
    // cb should be another anonymous function with no parameters.
    // cb is a closure so it will retain the reference to scorei .
    setTimeout(() => {
      // Inside cb, copy the code we used earlier to filter out duplicates.
      // Don't forget to change all references of scores[i] to scorei .
      // If scorei is not in uniq,
      if (!(scorei in uniq)){
        // then push scorei to ranks and put scorei in uniq set to true .
        ranks.push(scorei);
        uniq[scorei] = true;
      }
      // end if
        // Otherwise, ignore scores[i] --- it's a duplicate.
    },0)
    // end setTimeout

    // The forEach will still be synchronous, but the cb calls will be asynchronous.
})
// end forEach

// Log ranks so that we can see the filtered scores.
console.log(ranks)
// The output should be []. Why? You don't need to log this answer.
// Answer: The function that handles the pushing of scorei into ranks is pushed to the back of the task queue because it has been placed inside the setTimeout function.

// call setTimeout(cb, 0); cb should be an anonymous function with no parameters
setTimeout(() => {
    // Log ranks so that we can see the filtered scores.
    console.log(ranks)
    // The output should be [ 100, 90, 80, 75, 60 ] . Why? You don't need to log this answer.
    // Answer: This function (because of setTimeout function) is called after the function that handles the pushing of scorei into ranks is completed. Therefore, it is now possible to see the new value of ranks

    // Use a forEach loop to iterate over games.
    // The argument that you pass should be an anonymous function with one parameter: gamej .

        games.forEach((gamej) => {
        // In the body of this function, call setTimeout(cb, 0) where cb is an anonymous function.
        // cb takes no parameters and it is a closure so it will retain the reference to gamej .
          setTimeout(() => {
            // Inside cb, copy the code we used earlier to compute the rank of gamej .
            // Call binarySearch with arguments: ranks, gamej, 0, ranks.length-1 .
            // binarySearch will return an index, initialize a variable r equal to this index.
            // r is the rank assigned to gamej.
            var r = binarySearch(ranks,gamej,0,ranks.length-1);
            // If gamej is not in uniq,
            if (!(gamej in uniq)){
                // then splice gamej into ranks at index r and put gamej in uniq set to true .
                ranks.splice(r, 0, gamej);
                ranks[r] = gamej;
                uniq[gamej] = true;
            }
            // end if
            // Log the value of r (don't forget to add 1 to r since leaderboards are 1-indexed).
            console.log(r+1)
            // The output should be 6, 5, 4, 2, 1 .
          },0)
        // end setTimeout
        })
    // end forEach

    // Log ranks so that we can see the new leaderboard.
    console.log(ranks)

    // The output should be [100, 90, 80, 75, 60] . Why? You don't need to log the answer.

    // call setTimeout(cb, 0); cb should be an anonymous function with no parameters.
    setTimeout(() => {
      // In the body of cb, log ranks so that we can see the new leaderboard.
      console.log(ranks)
      // The output should be [102, 100, 90, 80, 77, 75, 65, 60, 50] . Why? Don't log.
      }
    // end setTimeout
    ,0)

// end setTimeout
},0)


// Part 3 - Questions.
// Please log your answers. It's okay to keep your answers short.
// You can use + to create multiline strings.
// For example:
//     var answer = "my answer" +
//     " goes here";

setTimeout(() => {

  // Question: Where do the cb calls go (the func in setTimeout(cb, 0))? When are they executed?

  console.log("A1. Back of the placed at the task queue after the defined number of milliseconds (2nd argument), and only executed after call stack is empty and this function is next in the task queue.")

  // Question: Can the use of forEach with setTimeout(cb, 0) cause stalling to occur?

  console.log("A2. Yes, if there were a case of a huge number of tasks in the task queue after the setTimeout places the function in the back of task queue, the program may take a long amount of time to respond and stall until everything in the call stack and task queue is finished.")

  // Question: Is the use of forEach with setTimeout(cb, 0) memory consumptious?
  console.log("A3. Yes. If the forEach is ran on an array that is of a huge size, there will be a huge number of timeout timers that are kept in memory and trying to run their internal countdown of defined milliseconds, taking up memory with each timer ticking down until they can be placed onto the task queue.")

  // Question: Why use forEach rather than a for-loop?
  console.log("A4. Remove need for declaration of addition variable, reducing chances of errors such as array out of bounds, forEach will run based on the existing array size. As such, improves readability of code.")

  // Question: Is this non-deterministic? Are there any race conditions? Why or why not?

  console.log("A5. Yes, this is non-determistic. There are no race conditions. Javascript places functions into task queues that are then polled into the call stack. Meaning operations are essentially atomic actions, and won't run into race conditions.")

  // Question: Can we make binarySearch async? Would our solution change if we did?

  console.log("A6. Yes, binarySearch can be done in a async fashion, to cater to the current solution, maybe the ranks can be split into two different portions that has a different min/max boundaries, then perform binary search based on the value of the new score.")

  // Question: Is there a better way to perform asynchronous programming?

  console.log("A7. Always mark out critical sections of code, taking careful notice of variables that are accessed by multiple possible threads, and use proper thread-safe functions when doing asynchronous programming.")

},50)


// The End. Great job!