"use client";

import DashboardSidebar from "./DashboardSidebar";
import DashboardNavbar from "./DashboardNavbar";

export default function DashboardShell({ children }) {
  return (
    <div className="flex h-screen overflow-hidden bg-[#F8F9FC]">

      {/* ================= Sidebar ================= */}

      <DashboardSidebar />

      {/* ================= Main Content ================= */}

      <div className="flex flex-1 flex-col overflow-hidden">

        {/* ================= Navbar ================= */}

        <DashboardNavbar />

        {/* ================= Content ================= */}

        <main
          className="
            flex-1
            overflow-y-auto
            px-8
            py-8
          "
        >
          <div
            className="
              mx-auto
              w-full
              max-w-[1600px]
            "
          >
            {children}
          </div>
        </main>

      </div>

    </div>
  );
}