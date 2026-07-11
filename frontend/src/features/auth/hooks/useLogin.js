"use client";

import { useMutation } from "@tanstack/react-query";
import { useRouter } from "next/navigation";
import { toast } from "sonner";

import authService from "../services/authService";
import userService from "../services/userService";

import useAuthStore from "@/store/authStore";

export default function useLogin() {
  const router = useRouter();

  const setUser = useAuthStore((state) => state.setUser);

  return useMutation({
    mutationFn: authService.login,

    onSuccess: async () => {
      try {
        // Login API has already stored HttpOnly Cookies.
        // Fetch the logged-in user's information.

        const response = await userService.getCurrentUser();

        // Save user in Zustand
        setUser(response.data);

        console.log("Current User:", response.data);

        // Success Notification
        toast.success("Login successful. Welcome back!");

        // Navigate to Dashboard
        router.push("/dashboard");

      } catch (error) {
        console.error("Failed to fetch current user:", error);

        toast.error(
          "Login succeeded, but failed to load user information."
        );
      }
    },

    onError: (error) => {
      console.error("Login Failed:", error);

      toast.error(
        error?.response?.data?.message ||
          "Invalid username or password."
      );
    },
  });
}