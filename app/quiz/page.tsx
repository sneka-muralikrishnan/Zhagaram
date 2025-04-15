"use client"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group"
import { Label } from "@/components/ui/label"
import { Progress } from "@/components/ui/progress"

const quizQuestions = [
  {
    id: 1,
    question: "Which ancient Tamil text is known as the 'Tamil Veda'?",
    options: [
      { id: "a", text: "Silappatikaram" },
      { id: "b", text: "Thirukkural" },
      { id: "c", text: "Thirumurai" },
      { id: "d", text: "Tolkappiyam" },
    ],
    correctAnswer: "b",
  },
  {
    id: 2,
    question: "Which classical dance form originated in Tamil Nadu?",
    options: [
      { id: "a", text: "Kathakali" },
      { id: "b", text: "Kuchipudi" },
      { id: "c", text: "Bharatanatyam" },
      { id: "d", text: "Odissi" },
    ],
    correctAnswer: "c",
  },
  {
    id: 3,
    question: "Which Tamil festival celebrates the harvest season?",
    options: [
      { id: "a", text: "Pongal" },
      { id: "b", text: "Deepavali" },
      { id: "c", text: "Navaratri" },
      { id: "d", text: "Karthigai Deepam" },
    ],
    correctAnswer: "a",
  },
  {
    id: 4,
    question: "Who is considered the author of Thirukkural?",
    options: [
      { id: "a", text: "Kambar" },
      { id: "b", text: "Thiruvalluvar" },
      { id: "c", text: "Ilango Adigal" },
      { id: "d", text: "Avvaiyar" },
    ],
    correctAnswer: "b",
  },
  {
    id: 5,
    question: "Which ancient Tamil kingdom was known for its maritime trade?",
    options: [
      { id: "a", text: "Chera" },
      { id: "b", text: "Chola" },
      { id: "c", text: "Pandya" },
      { id: "d", text: "Pallava" },
    ],
    correctAnswer: "b",
  },
]

export default function QuizPage() {
  const [currentQuestion, setCurrentQuestion] = useState(0)
  const [selectedOption, setSelectedOption] = useState("")
  const [score, setScore] = useState(0)
  const [showResult, setShowResult] = useState(false)
  const [answers, setAnswers] = useState<string[]>(Array(quizQuestions.length).fill(""))

  const handleOptionSelect = (value: string) => {
    setSelectedOption(value)
    const newAnswers = [...answers]
    newAnswers[currentQuestion] = value
    setAnswers(newAnswers)
  }

  const handleNext = () => {
    if (selectedOption === quizQuestions[currentQuestion].correctAnswer) {
      setScore(score + 1)
    }

    if (currentQuestion < quizQuestions.length - 1) {
      setCurrentQuestion(currentQuestion + 1)
      setSelectedOption(answers[currentQuestion + 1])
    } else {
      setShowResult(true)
    }
  }

  const handleRestart = () => {
    setCurrentQuestion(0)
    setSelectedOption("")
    setScore(0)
    setShowResult(false)
    setAnswers(Array(quizQuestions.length).fill(""))
  }

  const progress = ((currentQuestion + 1) / quizQuestions.length) * 100

  return (
    <div className="flex flex-col min-h-screen bg-[#e8d9c0]">
      <main className="flex-1 container px-4 py-8 md:px-6 md:py-12 mx-auto">
        <h1 className="text-3xl font-bold mb-6">Tamil Culture Quiz</h1>

        {!showResult ? (
          <Card className="max-w-2xl mx-auto border-[#c9b799]">
            <CardHeader className="bg-[#d9c7a9]">
              <CardTitle>
                Question {currentQuestion + 1} of {quizQuestions.length}
              </CardTitle>
              <CardDescription>{quizQuestions[currentQuestion].question}</CardDescription>
            </CardHeader>
            <CardContent className="pt-6">
              <RadioGroup value={selectedOption} onValueChange={handleOptionSelect}>
                {quizQuestions[currentQuestion].options.map((option) => (
                  <div key={option.id} className="flex items-center space-x-2 mb-3">
                    <RadioGroupItem value={option.id} id={`option-${option.id}`} />
                    <Label htmlFor={`option-${option.id}`}>{option.text}</Label>
                  </div>
                ))}
              </RadioGroup>
            </CardContent>
            <CardFooter className="flex flex-col gap-4">
              <Progress value={progress} className="w-full" />
              <Button
                onClick={handleNext}
                disabled={!selectedOption}
                className="w-full bg-[#b5a48c] hover:bg-[#a59484]"
              >
                {currentQuestion === quizQuestions.length - 1 ? "Finish Quiz" : "Next Question"}
              </Button>
            </CardFooter>
          </Card>
        ) : (
          <Card className="max-w-2xl mx-auto border-[#c9b799]">
            <CardHeader className="bg-[#d9c7a9]">
              <CardTitle>Quiz Results</CardTitle>
              <CardDescription>
                You scored {score} out of {quizQuestions.length}
              </CardDescription>
            </CardHeader>
            <CardContent className="pt-6">
              <div className="text-center mb-4">
                <p className="text-2xl font-bold mb-2">
                  {score === quizQuestions.length
                    ? "Perfect Score! 🎉"
                    : score >= quizQuestions.length / 2
                      ? "Good Job! 👏"
                      : "Keep Learning! 📚"}
                </p>
                <p className="text-gray-700">
                  {score === quizQuestions.length
                    ? "You're a Tamil culture expert!"
                    : score >= quizQuestions.length / 2
                      ? "You have a good understanding of Tamil culture."
                      : "Explore more about Tamil culture to improve your knowledge."}
                </p>
              </div>
            </CardContent>
            <CardFooter>
              <Button onClick={handleRestart} className="w-full bg-[#b5a48c] hover:bg-[#a59484]">
                Restart Quiz
              </Button>
            </CardFooter>
          </Card>
        )}
      </main>
    </div>
  )
}
