import Link from "next/link"
import Image from "next/image"
import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import { ChevronRight, ImageIcon, MessageSquare, Pizza, BookOpen } from "lucide-react"

export default function Home() {
  return (
    <div className="flex flex-col min-h-screen bg-[#e8d9c0]">
      <header className="sticky top-0 z-10 w-full border-b bg-[#e8d9c0]/80 backdrop-blur-sm">
        <div className="container flex items-center justify-between h-16 px-4 md:px-6 mx-auto">
          <Link href="/" className="flex items-center gap-2">
            <div className="w-10 h-10 bg-[#d9c7a9] rounded-full flex items-center justify-center">
              <span className="sr-only">ZHA GARAM</span>
              <Image
                src="/logo.png?height=40&width=40"
                alt="Logo"
                width={40}
                height={40}
                className="rounded-full"
              />
            </div>
            <span className="text-xl font-bold">ZHA GARAM</span>
          </Link>
          <nav className="hidden md:flex gap-6">
            <Link href="/" className="text-sm font-medium hover:underline underline-offset-4">
              Home
            </Link>
            <Link href="/gallery" className="text-sm font-medium hover:underline underline-offset-4">
              Gallery
            </Link>
            <Link href="/food" className="text-sm font-medium hover:underline underline-offset-4">
              Food
            </Link>
            <Link href="/quiz" className="text-sm font-medium hover:underline underline-offset-4">
              Quiz
            </Link>
            <Link href="/forum" className="text-sm font-medium hover:underline underline-offset-4">
              Forum
            </Link>
          </nav>
          <div className="flex items-center gap-2">
            <Link href="/login">
              <Button variant="outline" className="bg-[#d9c7a9] hover:bg-[#c9b799] border-[#c9b799]">
                Login
              </Button>
            </Link>
            <Link href="/signup">
              <Button className="bg-[#b5a48c] hover:bg-[#a59484] text-white">Sign Up</Button>
            </Link>
          </div>
        </div>
      </header>
      <main className="flex-1">
        <section className="w-full py-12 md:py-24 lg:py-32">
          <div className="container px-4 md:px-6 mx-auto">
            <div className="flex flex-col items-center justify-center space-y-4 text-center">
              <div className="space-y-2">
                <h1 className="text-3xl font-bold tracking-tighter sm:text-4xl md:text-5xl">
                  Discover the Rich Heritage of Tamil Culture
                </h1>
                <p className="max-w-[700px] text-gray-700 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed">
                  Explore the traditions, cuisine, arts, and history of one of the world's oldest civilizations.
                </p>
              </div>
            </div>
          </div>
        </section>
        <section className="w-full py-12 md:py-24 lg:py-32 bg-[#d9c7a9]/50">
          <div className="container px-4 md:px-6 mx-auto">
            <h2 className="text-2xl font-bold tracking-tighter sm:text-3xl mb-8 text-center">Explore Tamil Culture</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
              <Card className="bg-[#e8d9c0] border-[#c9b799]">
                <CardContent className="p-6 flex flex-col items-center text-center space-y-4">
                  <div className="p-2 bg-[#d9c7a9] rounded-full">
                    <ImageIcon className="h-6 w-6" />
                  </div>
                  <h3 className="text-xl font-bold">Cultural Gallery</h3>
                  <p className="text-gray-700">
                    Explore visual representations of Tamil art, architecture, and cultural artifacts.
                  </p>
                  <Link href="/gallery">
                    <Button variant="outline" className="mt-2 bg-[#d9c7a9] hover:bg-[#c9b799] border-[#c9b799]">
                      View Gallery <ChevronRight className="ml-2 h-4 w-4" />
                    </Button>
                  </Link>
                </CardContent>
              </Card>
              <Card className="bg-[#e8d9c0] border-[#c9b799]">
                <CardContent className="p-6 flex flex-col items-center text-center space-y-4">
                  <div className="p-2 bg-[#d9c7a9] rounded-full">
                    <Pizza className="h-6 w-6" />
                  </div>
                  <h3 className="text-xl font-bold">Traditional Foods</h3>
                  <p className="text-gray-700">Discover the flavors and recipes of authentic Tamil cuisine.</p>
                  <Link href="/food">
                    <Button variant="outline" className="mt-2 bg-[#d9c7a9] hover:bg-[#c9b799] border-[#c9b799]">
                      Explore Foods <ChevronRight className="ml-2 h-4 w-4" />
                    </Button>
                  </Link>
                </CardContent>
              </Card>
              <Card className="bg-[#e8d9c0] border-[#c9b799]">
                <CardContent className="p-6 flex flex-col items-center text-center space-y-4">
                  <div className="p-2 bg-[#d9c7a9] rounded-full">
                    <BookOpen className="h-6 w-6" />
                  </div>
                  <h3 className="text-xl font-bold">Cultural Quiz</h3>
                  <p className="text-gray-700">Test your knowledge about Tamil history, language, and traditions.</p>
                  <Link href="/quiz">
                    <Button variant="outline" className="mt-2 bg-[#d9c7a9] hover:bg-[#c9b799] border-[#c9b799]">
                      Take Quiz <ChevronRight className="ml-2 h-4 w-4" />
                    </Button>
                  </Link>
                </CardContent>
              </Card>
              <Card className="bg-[#e8d9c0] border-[#c9b799]">
                <CardContent className="p-6 flex flex-col items-center text-center space-y-4">
                  <div className="p-2 bg-[#d9c7a9] rounded-full">
                    <MessageSquare className="h-6 w-6" />
                  </div>
                  <h3 className="text-xl font-bold">Discussion Forum</h3>
                  <p className="text-gray-700">
                    Join conversations about Tamil culture, ask questions, and share insights.
                  </p>
                  <Link href="/forum">
                    <Button variant="outline" className="mt-2 bg-[#d9c7a9] hover:bg-[#c9b799] border-[#c9b799]">
                      Join Forum <ChevronRight className="ml-2 h-4 w-4" />
                    </Button>
                  </Link>
                </CardContent>
              </Card>
            </div>
          </div>
        </section>
      </main>
      <footer className="w-full border-t bg-[#d9c7a9] py-6">
        <div className="container flex flex-col items-center justify-center gap-4 px-4 md:px-6 text-center mx-auto">
          <p className="text-sm text-gray-700">© 2025 ZHA GARAM. All rights reserved.</p>
        </div>
      </footer>
    </div>
  )
}
