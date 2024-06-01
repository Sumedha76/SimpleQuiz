
import java.util.Scanner;
import java.lang.String;


//Questions for quiz
class Question_Bank
{

    //First Round questions - beginner
    String []question_1={"1. What is the largest ocean on Earth?","2. How many colors are there in a rainbow?","3.What is the capital of France?","4. What is the currency of Japan?","5. What is the name of the tallest mountain in the world?","6. What is the name of the tallest mountain in the world?","7. What is the largest land animal?","8. What do we call baby cows?","9. Which planet is known as the Red Planet?","10. Who is the first Prime Minister of  India?"};

    //Answers for first round 
    String answers_1[]={"The Pacific Ocean","7","Paris","Japanese Yen","Mount Everest","7","The African Bush Elephant","Calves","Mars","Jawaharlal Nehru"};


    //Second round questions
    String questions_2[]={"1. What is the name of the world's largest desert ?","2. What is the largest bone in the human body?","3. In which year did World War II begin?","4. What is the name of the famous leaning tower in Italy?","5. What is the chemical symbol for water?","6. What is the largest freshwater lake by volume?","7. Who wrote the famous play \"Hamlet\"? " };

    //Second round answers
    String answers_2[]={"The Sahara Desert","thigh bone","1939","The Leaning Tower of Pisa","H2O","Lake Baikal","William Shakespeare"};


    //Third round questions
    String []questions_3 ={"1. What is the rarest blood type in the world? ","2.What mountain range separates Europe and Asia?","3. What is the scientific name for a group of owls?","4. In which country was the first commercial nuclear power plant built?","5. In which mountain range is Mount Everst located?"};

    //Third round answers
    String []answers_3={"AB negative","The Ural Mountains","parliament","Russia","The Himalayas"};

    //Array of questions of all the rounds
    String questions[][]={question_1,questions_2,questions_3};

    //Array of answers of all the rounds
    String answers[][]={answers_1,answers_2,answers_3};


    //Default values
    int number_of_questions_1=0,number_of_questions_2=0,number_of_questions_3=0;
    
    //Paramaterized constructor to initialize number of quesitons in each round
    Question_Bank(int one, int two ,int three)
    {
        number_of_questions_1 = one ;
        number_of_questions_2= two ;
        number_of_questions_3 = three;
    }
}


//Calculate quiz points
class points
{
    
    //Function to calculate and return points earned in the form of integer array
    int[] game_body(int round,int number_of_questions,double player_points, Question_Bank  question_Bank, double correct_answer_factor, double wrong_answer_factor)
    {
        int total_time_taken=0;
        int number_of_correct_answers=0;
        int number_of_wrong_answers=0;
        int number_of_unanswered=0;
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        int initial_points=1000;
        for(int questions=0;questions < number_of_questions; questions++) //Loop until all the questions are completed in the current round
        {
            
        long startTime = System.currentTimeMillis();

        //Display question
        System.out.println("\n"+question_Bank.questions[round][questions]);
        
        //Accept the answer 
        String player_answer=input.nextLine();
        
        
        long endTime = System.currentTimeMillis();

        //Time taken to answer one question   
        double time_taken= (double)(endTime-startTime)/1000;

        //Total time taken to complete the current round
        total_time_taken+=time_taken;
        
        //If player doesn't answer the question, no modification shall be done to player_points
        if(player_answer.isBlank())
        {
            number_of_unanswered++;
        }

        else if(player_answer.equalsIgnoreCase(question_Bank.answers[round][questions])) //If player answers correctly
        {
            player_points+=(initial_points*correct_answer_factor/time_taken); //Reward the points based on the time taken to answer
            number_of_correct_answers++;
        }
        else if(player_answer.equalsIgnoreCase(question_Bank.answers_1[questions])==false) // If player answer in incorrect
        {
            player_points-=(initial_points*wrong_answer_factor/time_taken); //Deduct the points based on time taken to answer
            number_of_wrong_answers++;
        }

        }
        
        //result of the current round
        int[] result= {(int)player_points,number_of_correct_answers,number_of_wrong_answers,number_of_unanswered,total_time_taken}; 

        return result;
     
    }
    
}

//Main 
public class Quizgame
{
    static void display_results(int round,int []results,int number_of_questions)
    {
        System.out.println("\n\nResult of Round "+round+":\nNumber of questions: "+number_of_questions+"\nCorrect Answer: "+ results[1]+"\nWrong Answer: "+results[2]+"\nUnanswered: "+results[3]+"\n\n"+"Total Points: "+results[0]+"\nTotal Time taken: "+results[4]+" seconds");
    }
    public static void main(String []args)
    {
        Scanner input = new Scanner(System.in);
    
        System.out.println("\n-------- General Knowledge Quiz -------");
        System.out.println("Enter your name");

        //Input player name
        String name =input.nextLine();
        String player_name=name.substring(0,1).toUpperCase()+name.substring(1);

        //object of class points 
        points p =new points();

        //Default value
        int number_of_rounds=0;

        System.out.println("\nHi "+player_name+"!"+"\nEnter the number of rounds you wish to play \n(Difficulty increases with each round. Min 60% to progress. Max 3 rounds )\n");

        //Number of rounds the player wants to play
        number_of_rounds=input.nextInt();
        if(number_of_rounds>3||number_of_rounds<=0)
        {
            System.out.println("Invalid input");
            System.exit(0);
        }

            input.nextLine();
            System.out.println("Enter number of questions you wish to have in first round (max: 10)");
            //Take input for only first round 
            int number_of_questions_1 = input.nextInt();
            int number_of_questions_2=0;
            int number_of_questions_3 =0;
            
        if (number_of_rounds >=2)
        {
            System.out.println("\nEnter number of questions you wish to have in second round (max: 7)");
            number_of_questions_2 = input.nextInt();
        }
        
        if(number_of_rounds==3)
        {
            System.out.println("\nEnter number of questions to have in third round (max: 5) ");
            number_of_questions_3 = input.nextInt();
        }

    //Initalize the number of questions for each round based on user input
    Question_Bank question_Bank = new Question_Bank(number_of_questions_1, number_of_questions_2, number_of_questions_3);
    
    double player_points=1000; // The points player has before game starts
    

    double  correct_answer_factor=0.5, wrong_answer_factor=0.5; // Multiplier factor for correct and wrong answer respectively

    //Array to store the result of round 1
    int[] result_round_1 = new int[5];
    //Array to store results of round 2
    int []result_round_2 = new int[5];
    //Array to store result of round 3
    int []result_round_3 = new int[5];

    for(int rounds=1;rounds<=number_of_rounds;rounds++)
    {   
        int initial_points=1000;

        //Maximum points for round 1 (considering that averge time taken to answer each question is 15 seconds)
        float total_1=(float)(initial_points+(number_of_questions_1*correct_answer_factor)/(15*number_of_questions_1));

       
        if(rounds==1){
        System.out.println("\n---------Beginner Level---------");
        result_round_1=p.game_body(0,number_of_questions_1,player_points,question_Bank,correct_answer_factor,wrong_answer_factor);
        player_points= (double)result_round_1[0];

        
        if (player_points>= 0.6*total_1 && player_points!=initial_points)
            {
                System.out.println("\n\nCongratulations! "+player_name+"\nYou have cleared Round 1");
            }
            else
            {
                System.out.println("\n\nOpps! Better Luck Next Time");
                display_results(1,result_round_1, number_of_questions_1);
                break;
            }
        }
        
        //Display the results if player plays only one round
        if(number_of_rounds ==1 )
        {
            display_results(1,result_round_1, number_of_questions_1);
            break;
        }
        
        //Maximum points for round 2 (considering that averge time taken to answer each question is 20 seconds)
        int total_2=(int)(total_1+(correct_answer_factor*number_of_questions_2)/(20*number_of_questions_2));
        if(rounds==2){
            
            System.out.println("\n---------Intermediate Level---------");
           
            result_round_2=p.game_body(1,number_of_questions_2,player_points,question_Bank,correct_answer_factor,wrong_answer_factor);
            player_points= (double)result_round_2[0];

            if (player_points>= 0.6*total_2)
            {
                System.out.println("\n\nCongratulations! "+player_name+"\nYou have cleared Round 2");
            }
            else
            {
                System.out.println("\n\nOpps! Better Luck Next Time");
                display_results(1,result_round_1, number_of_questions_1);
                display_results(2,result_round_2, number_of_questions_2);
                break;
            }
            }

            //Display the results of round 2 if the maximum number of rounds played is 2
            if(number_of_rounds==2 && rounds==2)
            {
                display_results(1,result_round_1, number_of_questions_1);
                display_results(2,result_round_2, number_of_questions_2);
                break;
            }

            

            //Maximum points for round 2 (considering that averge time taken to answer each question is 30 seconds)
            float total_3=(float)(total_2+(correct_answer_factor*number_of_questions_3)/(30*number_of_questions_3));
            if(rounds==3){
                
                System.out.println("\n---------Difficult Level---------");
                input.nextLine();
                result_round_3=p.game_body(2,number_of_questions_2,player_points,question_Bank,correct_answer_factor,wrong_answer_factor);
                player_points= (double)result_round_2[0]; //player points after second round


                if (player_points>= 0.6*total_3)
                {
                    System.out.println("\n\nCongratulations! "+player_name+"\nYou have cleared Round 3");
                }
                else
                {
                    System.out.println("\n\nOpps! Better Luck Next Time");
                    display_results(1,result_round_1, number_of_questions_1);
                    display_results(2,result_round_2, number_of_questions_2);
                    display_results(3,result_round_3, number_of_questions_3);
                    break;
                }
                }

               //Display the results of round 3
                if(number_of_rounds==3&& rounds==3)
                {
                    display_results(1,result_round_1, number_of_questions_1);
                    display_results(2,result_round_2, number_of_questions_2);
                    display_results(3,result_round_3, number_of_questions_3);  
                }
        
        correct_answer_factor+=0.1; //Increment correct_answer_factor when current round completes
        wrong_answer_factor+=0.1; //Increment wrong_answer_factor when current round completes
    }
    input.close();
}

}