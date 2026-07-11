"use client";

export default function Error({ error, reset }) {
  return (
    <div className="flex flex-col items-center justify-center h-full gap-4">

      <h2 className="text-2xl font-semibold text-red-600">
        Something went wrong
      </h2>

      <p className="text-gray-500">
        {error.message}
      </p>

      <button
        onClick={reset}
        className="rounded-xl bg-[var(--primary)] px-5 py-2 text-white"
      >
        Try Again
      </button>

    </div>
  );
}