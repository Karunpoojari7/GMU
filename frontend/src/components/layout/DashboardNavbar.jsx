"use client";

import { Bell, Search, Moon, ChevronRight } from "lucide-react";
import useAuthStore from "@/store/authStore";

export default function DashboardNavbar() {
  const user = useAuthStore((state) => state.user);

  return (
    <header
      className="
        h-[72px]
        border-b
        border-gray-200
        bg-white
        flex
        items-center
        justify-between
        px-8
      "
    >
      {/* ================= Left ================= */}

      <div>

        <div className="flex items-center gap-2 text-sm text-gray-500">

          <span>Dashboard</span>

          <ChevronRight size={15} />

          <span className="text-[var(--text)] font-medium">
            Overview
          </span>

        </div>

        <h1 className="mt-1 text-2xl font-semibold font-inter">
          Welcome, {user?.name || "Administrator"}
        </h1>

      </div>

      {/* ================= Right ================= */}

      <div className="flex items-center gap-4">

        {/* Search */}

        <div
          className="
            hidden
            lg:flex
            items-center
            gap-2
            rounded-xl
            border
            border-gray-200
            px-4
            py-2
            w-[280px]
          "
        >
          <Search size={17} className="text-gray-400" />

          <input
            placeholder="Search..."
            className="
              w-full
              outline-none
              text-sm
              bg-transparent
            "
          />

        </div>

        {/* Notifications */}

        <button
          className="
            h-10
            w-10
            rounded-xl
            border
            border-gray-200
            flex
            items-center
            justify-center
            hover:bg-gray-100
          "
        >
          <Bell size={18} />
        </button>

        {/* Theme */}

        <button
          className="
            h-10
            w-10
            rounded-xl
            border
            border-gray-200
            flex
            items-center
            justify-center
            hover:bg-gray-100
          "
        >
          <Moon size={18} />
        </button>

      </div>

    </header>
  );
}