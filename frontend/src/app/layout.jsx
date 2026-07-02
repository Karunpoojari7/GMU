
import { Outfit } from "next/font/google";
import Provider from "../../components/app/providers"
const outfit = Outfit({
  subsets: ["latin"],
  variable: "--font-outfit",
});
export const metadata= {
  title: "GMU Lead Management",
  description: "CRM and Lead Management System",
};

export default function RootLayout({
  children,
}) {
  return (
    <html
      lang="en"
      className={`${outfit.variable} h-full antialiased`}
    >
      <body className={`${outfit.className} min-h-full flex flex-col`}>
            <Provider>
              {children}
            </Provider>
      </body>
    </html>
  );
}
