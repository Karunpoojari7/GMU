import {
  LayoutDashboard,
  Users,
  UserCog,
  Building2,
  ChartColumn,
  Settings,
  ClipboardList,
  CalendarClock,
  User,
} from "lucide-react";

export const NAVIGATION = {
  ADMIN: [
    {
      id: "dashboard",
      title: "Dashboard",
      href: "/dashboard",
      icon: LayoutDashboard,
    },
    {
      id: "leads",
      title: "Lead Management",
      href: "/dashboard/leads",
      icon: Users,
    },
    {
      id: "people",
      title: "People",
      href: "/dashboard/people",
      icon: UserCog,
    },
    {
      id: "departments",
      title: "Departments",
      href: "/dashboard/departments",
      icon: Building2,
    },
    {
      id: "reports",
      title: "Reports",
      href: "/dashboard/reports",
      icon: ChartColumn,
    },
    {
      id: "settings",
      title: "Settings",
      href: "/dashboard/settings",
      icon: Settings,
    },
  ],

  COUNSELLOR: [
    {
      id: "dashboard",
      title: "Dashboard",
      href: "/dashboard",
      icon: LayoutDashboard,
    },
    {
      id: "my-leads",
      title: "My Leads",
      href: "/dashboard/my-leads",
      icon: ClipboardList,
    },
    {
      id: "followups",
      title: "Follow Ups",
      href: "/dashboard/followups",
      icon: CalendarClock,
    },
    {
      id: "profile",
      title: "Profile",
      href: "/dashboard/profile",
      icon: User,
    },
  ],
};