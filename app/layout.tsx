import type React from "react"
import type { Metadata } from "next"
import "./globals.css"

export const metadata: Metadata = {
  title: "ZHA GARAM",
  description: "Created with v0",
  generator: "v0.dev",
  icons: {
    icon: "/logo.png", // Favicon (browser tab icon)
    apple: "/logo.png", // Apple touch icon
  },
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  )
}
