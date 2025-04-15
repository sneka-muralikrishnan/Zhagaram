import Image from "next/image"
import { Card, CardContent } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

export default function GalleryPage() {
  return (
    <div className="flex flex-col min-h-screen bg-[#e8d9c0]">
      <main className="flex-1 container px-4 py-8 md:px-6 md:py-12 mx-auto">
        <h1 className="text-3xl font-bold mb-6">Tamil Cultural Gallery</h1>

        <Tabs defaultValue="art" className="w-full">
          <TabsList className="grid w-full grid-cols-4 bg-[#d9c7a9]">
            <TabsTrigger value="art">Art</TabsTrigger>
            <TabsTrigger value="architecture">Architecture</TabsTrigger>
            <TabsTrigger value="festivals">Festivals</TabsTrigger>
            <TabsTrigger value="dance">Dance & Music</TabsTrigger>
          </TabsList>

          <TabsContent value="art" className="mt-6">
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {[1, 2, 3, 4, 5, 6].map((item) => (
                <Card key={item} className="overflow-hidden border-[#c9b799]">
                  <CardContent className="p-0">
                    <div className="relative aspect-square">
                      <Image
                        src={`/art${item}.jpeg?height=300&width=300&text=Tamil+Art+${item}`}
                        alt={`Tamil Art ${item}`}
                        fill
                        className="object-cover"
                      />
                    </div>
                    <div className="p-4 bg-white">
                      <h3 className="font-medium">Traditional Tamil Art {item}</h3>
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>

          <TabsContent value="architecture" className="mt-6">
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {[1, 2, 3, 4, 5, 6].map((item) => (
                <Card key={item} className="overflow-hidden border-[#c9b799]">
                  <CardContent className="p-0">
                    <div className="relative aspect-square">
                      <Image
                        src={`/ar${item}.jpeg?height=300&width=300&text=Tamil+Temple+${item}`}
                        alt={`Tamil Temple ${item}`}
                        fill
                        className="object-cover"
                      />
                    </div>
                    <div className="p-4 bg-white">
                      <h3 className="font-medium">Ancient Temple Architecture {item}</h3>
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>

          <TabsContent value="festivals" className="mt-6">
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {[1, 2, 3, 4, 5, 6].map((item) => (
                <Card key={item} className="overflow-hidden border-[#c9b799]">
                  <CardContent className="p-0">
                    <div className="relative aspect-square">
                      <Image
                        src={`/fest${item}.jpeg?height=300&width=300&text=Tamil+Festival+${item}`}
                        alt={`Tamil Festival ${item}`}
                        fill
                        className="object-cover"
                      />
                    </div>
                    <div className="p-4 bg-white">
                      <h3 className="font-medium">Traditional Festival {item}</h3> 
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>

          <TabsContent value="dance" className="mt-6">
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {[1, 2, 3, 4, 5, 6].map((item) => (
                <Card key={item} className="overflow-hidden border-[#c9b799]">
                  <CardContent className="p-0">
                    <div className="relative aspect-square">
                      <Image
                        src={`/dm${item}.jpeg?height=300&width=300&text=Tamil+Dance+${item}`}
                        alt={`Tamil Dance ${item}`}
                        fill
                        className="object-cover"
                      />
                    </div>
                    <div className="p-4 bg-white">
                      <h3 className="font-medium">Classical Dance Form {item}</h3>
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>
        </Tabs>
      </main>
    </div>
  )
}
