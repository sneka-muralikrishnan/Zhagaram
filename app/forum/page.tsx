"use client"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Textarea } from "@/components/ui/textarea"
import { MessageCircle, ThumbsUp, Reply } from "lucide-react"

// Sample forum data
const initialPosts = [
  {
    id: 1,
    author: "Ramesh Kumar",
    avatar: "RK",
    date: "2 days ago",
    content:
      "I recently visited the Meenakshi Amman Temple in Madurai. The architecture is absolutely stunning! Has anyone else been there recently?",
    likes: 15,
    replies: [
      {
        id: 101,
        author: "Priya S",
        avatar: "PS",
        date: "1 day ago",
        content:
          "Yes, I was there last month! The colorful sculptures and the thousand pillar hall are breathtaking. Did you get to see the evening ceremony?",
        likes: 8,
      },
      {
        id: 102,
        author: "Karthik R",
        avatar: "KR",
        date: "1 day ago",
        content:
          "The temple is a masterpiece of Dravidian architecture. I recommend visiting during the Chithirai festival if you get a chance.",
        likes: 5,
      },
    ],
  },
  {
    id: 2,
    author: "Lakshmi Narayanan",
    avatar: "LN",
    date: "4 days ago",
    content:
      "I'm trying to learn more about traditional Tamil musical instruments. Can anyone recommend resources or places where I can see performances?",
    likes: 10,
    replies: [
      {
        id: 201,
        author: "Arun V",
        avatar: "AV",
        date: "3 days ago",
        content:
          "The Nadaswaram and Thavil are two iconic instruments. You can often see performances at temples during festivals. The Music Academy in Chennai also hosts concerts regularly.",
        likes: 7,
      },
    ],
  },
  {
    id: 3,
    author: "Divya Prakash",
    avatar: "DP",
    date: "1 week ago",
    content:
      "What's your favorite Tamil dish? I'm trying to expand my cooking skills and would love some authentic recipes to try at home.",
    likes: 22,
    replies: [
      {
        id: 301,
        author: "Meena R",
        avatar: "MR",
        date: "6 days ago",
        content:
          "Chettinad chicken curry is my absolute favorite! The blend of spices is incredible. I can share my family recipe if you're interested.",
        likes: 12,
      },
      {
        id: 302,
        author: "Suresh K",
        avatar: "SK",
        date: "5 days ago",
        content:
          "You should definitely try making Pongal - it's a comforting rice and lentil dish that's relatively easy to make but so delicious!",
        likes: 9,
      },
      {
        id: 303,
        author: "Anitha J",
        avatar: "AJ",
        date: "4 days ago",
        content:
          "Rasam! It's a tangy soup that's perfect for rainy days. My grandmother's recipe uses fresh tomatoes and homemade rasam powder.",
        likes: 14,
      },
    ],
  },
]

export default function ForumPage() {
  const [posts, setPosts] = useState(initialPosts)
  const [newPost, setNewPost] = useState("")
  const [replyText, setReplyText] = useState<{ [key: number]: string }>({})
  const [showReplyForm, setShowReplyForm] = useState<{ [key: number]: boolean }>({})

  const handlePostSubmit = () => {
    if (!newPost.trim()) return

    const newPostObj = {
      id: posts.length + 1,
      author: "You",
      avatar: "YO",
      date: "Just now",
      content: newPost,
      likes: 0,
      replies: [],
    }

    setPosts([newPostObj, ...posts])
    setNewPost("")
  }

  const handleReplySubmit = (postId: number) => {
    if (!replyText[postId]?.trim()) return

    const updatedPosts = posts.map((post) => {
      if (post.id === postId) {
        const newReply = {
          id: (post.replies.length > 0 ? Math.max(...post.replies.map((r) => r.id)) : postId * 100) + 1,
          author: "You",
          avatar: "YO",
          date: "Just now",
          content: replyText[postId],
          likes: 0,
        }
        return {
          ...post,
          replies: [...post.replies, newReply],
        }
      }
      return post
    })

    setPosts(updatedPosts)
    setReplyText({ ...replyText, [postId]: "" })
    setShowReplyForm({ ...showReplyForm, [postId]: false })
  }

  const toggleReplyForm = (postId: number) => {
    setShowReplyForm({
      ...showReplyForm,
      [postId]: !showReplyForm[postId],
    })
  }

  const handleLike = (postId: number, replyId?: number) => {
    const updatedPosts = posts.map((post) => {
      if (replyId) {
        if (post.id === postId) {
          const updatedReplies = post.replies.map((reply) => {
            if (reply.id === replyId) {
              return { ...reply, likes: reply.likes + 1 }
            }
            return reply
          })
          return { ...post, replies: updatedReplies }
        }
      } else if (post.id === postId) {
        return { ...post, likes: post.likes + 1 }
      }
      return post
    })

    setPosts(updatedPosts)
  }

  return (
    <div className="flex flex-col min-h-screen bg-[#e8d9c0]">
      <main className="flex-1 container px-4 py-8 md:px-6 md:py-12 mx-auto">
        <h1 className="text-3xl font-bold mb-6">Tamil Culture Discussion Forum</h1>

        <Card className="mb-8 border-[#c9b799]">
          <CardHeader>
            <CardTitle>Start a New Discussion</CardTitle>
          </CardHeader>
          <CardContent>
            <Textarea
              placeholder="Share your thoughts, questions, or experiences about Tamil culture..."
              value={newPost}
              onChange={(e) => setNewPost(e.target.value)}
              className="min-h-[100px]"
            />
          </CardContent>
          <CardFooter>
            <Button onClick={handlePostSubmit} className="bg-[#b5a48c] hover:bg-[#a59484]">
              Post Discussion
            </Button>
          </CardFooter>
        </Card>

        <div className="space-y-6">
          {posts.map((post) => (
            <Card key={post.id} className="border-[#c9b799]">
              <CardHeader className="flex flex-row items-start gap-4 pb-2">
                <Avatar>
                  <AvatarImage src={`/placeholder.svg?text=${post.avatar}`} alt={post.author} />
                  <AvatarFallback>{post.avatar}</AvatarFallback>
                </Avatar>
                <div className="grid gap-1">
                  <CardTitle className="text-lg">{post.author}</CardTitle>
                  <p className="text-sm text-gray-500">{post.date}</p>
                </div>
              </CardHeader>
              <CardContent>
                <p className="text-gray-700">{post.content}</p>
              </CardContent>
              <CardFooter className="flex justify-between border-t pt-4">
                <div className="flex gap-4">
                  <Button
                    variant="ghost"
                    size="sm"
                    className="flex items-center gap-1 text-gray-600"
                    onClick={() => handleLike(post.id)}
                  >
                    <ThumbsUp className="h-4 w-4" /> {post.likes}
                  </Button>
                  <Button
                    variant="ghost"
                    size="sm"
                    className="flex items-center gap-1 text-gray-600"
                    onClick={() => toggleReplyForm(post.id)}
                  >
                    <Reply className="h-4 w-4" /> Reply
                  </Button>
                </div>
                <div className="flex items-center gap-1 text-gray-600">
                  <MessageCircle className="h-4 w-4" />
                  <span>{post.replies.length}</span>
                </div>
              </CardFooter>

              {showReplyForm[post.id] && (
                <div className="px-6 pb-4">
                  <Textarea
                    placeholder="Write your reply..."
                    value={replyText[post.id] || ""}
                    onChange={(e) => setReplyText({ ...replyText, [post.id]: e.target.value })}
                    className="mb-2"
                  />
                  <Button
                    onClick={() => handleReplySubmit(post.id)}
                    className="bg-[#b5a48c] hover:bg-[#a59484]"
                    size="sm"
                  >
                    Submit Reply
                  </Button>
                </div>
              )}

              {post.replies.length > 0 && (
                <div className="px-6 pb-4 pt-2">
                  <h3 className="font-medium mb-3">Replies</h3>
                  <div className="space-y-4 pl-6 border-l-2 border-[#d9c7a9]">
                    {post.replies.map((reply) => (
                      <div key={reply.id} className="grid gap-2">
                        <div className="flex items-start gap-2">
                          <Avatar className="h-6 w-6">
                            <AvatarImage src={`/placeholder.svg?text=${reply.avatar}`} alt={reply.author} />
                            <AvatarFallback>{reply.avatar}</AvatarFallback>
                          </Avatar>
                          <div>
                            <div className="flex items-center gap-2">
                              <span className="font-medium text-sm">{reply.author}</span>
                              <span className="text-xs text-gray-500">{reply.date}</span>
                            </div>
                            <p className="text-sm text-gray-700 mt-1">{reply.content}</p>
                            <Button
                              variant="ghost"
                              size="sm"
                              className="flex items-center gap-1 text-gray-600 h-6 mt-1"
                              onClick={() => handleLike(post.id, reply.id)}
                            >
                              <ThumbsUp className="h-3 w-3" /> {reply.likes}
                            </Button>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </Card>
          ))}
        </div>
      </main>
    </div>
  )
}
