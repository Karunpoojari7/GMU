"use client";

import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { LogOut } from "lucide-react";

import { IMAGES } from "@/constants/images";
import { NAVIGATION } from "@/config/navigation";
import useAuthStore from "@/store/authStore";

export default function DashboardSidebar() {
  const pathname = usePathname();

  const user = useAuthStore((state) => state.user);

  const menu =
    NAVIGATION[user?.userGroup] || NAVIGATION.ADMIN;

  return (
    <aside
      className="
        w-[260px]
        h-screen
        border-r
        border-gray-200
        bg-white
        flex
        flex-col
      "
    >
      {/* ================= Logo ================= */}

      <div className="h-[72px] px-6 flex items-center border-b border-gray-100">

        <Image
          src={IMAGES.LOGO}
          alt="GM University"
          width={46}
          height={46}
          className="rounded-full"
        />

        <div className="ml-3">

          <h2 className="font-semibold text-[15px] font-inter">
            GM University
          </h2>

          <p className="text-xs text-gray-500">
            Lead Management ERP
          </p>

        </div>

      </div>

      {/* ================= Navigation ================= */}

      <nav className="flex-1 px-4 py-6">

        <div className="space-y-2">

          {menu.map((item) => {

            const Icon = item.icon;

            const active = pathname === item.href;

            return (
              <Link
                key={item.id}
                href={item.href}
                className={`
                  flex
                  items-center
                  gap-3
                  rounded-xl
                  px-4
                  py-3
                  transition-all
                  duration-200

                  ${
                    active
                      ? "bg-[var(--primary)] text-white"
                      : "text-gray-600 hover:bg-gray-100"
                  }
                `}
              >
                <Icon size={19} />

                <span className="font-medium font-inter text-sm">
                  {item.title}
                </span>

              </Link>
            );

          })}

        </div>

      </nav>

      {/* ================= Footer ================= */}

      <div className="border-t border-gray-100 p-5">

        <div className="mb-4">

          <p className="font-semibold text-sm">

            {user?.name || "Administrator"}

          </p>

          <p className="text-xs text-gray-500">

            {user?.empId || "EMP001"}

          </p>

        </div>

        <button
          className="
            flex
            w-full
            items-center
            gap-3
            rounded-xl
            border
            border-gray-200
            px-4
            py-3
            text-sm
            text-red-500
            hover:bg-red-50
            transition
          "
        >
          <LogOut size={18} />

          Logout

        </button>

      </div>

    </aside>
  );
}