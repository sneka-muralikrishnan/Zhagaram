import Image from "next/image"
import { Card, CardContent } from "@/components/ui/card"

const foods = [
  {
    id: 1,
    name: "Dosa",
    description:
      "A thin crispy crepe made from fermented rice and lentil batter, typically served with sambar and chutney.",
    image: "/dosa.jpeg?height=300&width=300&text=Dosa",
  },
  {
    id: 2,
    name: "Idli",
    description: "Steamed rice cakes made from fermented rice and lentil batter, served with sambar and chutney.",
    image: "/idly.jpeg?height=300&width=300&text=Idli",
  },
  {
    id: 3,
    name: "Pongal",
    description: "A savory rice dish cooked with lentils and seasoned with cumin, pepper, and cashews.",
    image: "/pongal.jpeg?height=300&width=300&text=Pongal",
  },
  {
    id: 4,
    name: "Sambar",
    description: "A lentil-based vegetable stew cooked with tamarind and spices.",
    image: "/sambar.jpeg?height=300&width=300&text=Sambar",
  },
  {
    id: 5,
    name: "Rasam",
    description: "A tangy soup made with tamarind juice, tomato, pepper, and various spices.",
    image: "/rasam.jpeg?height=300&width=300&text=Rasam",
  },
  {
    id: 6,
    name: "Chettinad Chicken",
    description: "A spicy chicken curry from the Chettinad region, known for its aromatic spices.",
    image: "/chicken.jpeg?height=300&width=300&text=Chettinad+Chicken",
  },
  {
    id: 7,
    name: "Appam",
    description: "A type of pancake made with fermented rice batter and coconut milk.",
    image: "/appam.jpeg?height=300&width=300&text=Appam",
  },
  {
    id: 8,
    name: "Payasam",
    description: "A sweet pudding made with milk, sugar, and ingredients like vermicelli or rice.",
    image: "/payasam.jpeg?height=300&width=300&text=Payasam",
  },
  {
    id: 9,
    name: "Murukku",
    description: "A crunchy savory snack made from rice flour and urad dal flour.",
    image: "/murukku.jpeg?height=300&width=300&text=Murukku",
  },
]

export default function FoodPage() {
  return (
    <div className="flex flex-col min-h-screen bg-[#e8d9c0]">
      <main className="flex-1 container px-4 py-8 md:px-6 md:py-12 mx-auto">
        <h1 className="text-3xl font-bold mb-2">Traditional Tamil Cuisine</h1>
        <p className="text-gray-700 mb-8">
          Explore the rich and diverse flavors of traditional Tamil cuisine, known for its aromatic spices, vegetarian
          delicacies, and unique cooking techniques.
        </p>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {foods.map((food) => (
            <Card key={food.id} className="overflow-hidden border-[#c9b799]">
              <CardContent className="p-0">
                <div className="relative h-48">
                  <Image src={food.image || "/placeholder.svg"} alt={food.name} fill className="object-cover" />
                </div>
                <div className="p-4 bg-white">
                  <h3 className="font-medium text-lg">{food.name}</h3>
                  <p className="text-sm text-gray-600 mt-2">{food.description}</p>
                </div>
              </CardContent>
            </Card>
          ))}
        </div>
      </main>
    </div>
  )
}
