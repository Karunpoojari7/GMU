export default function DashboardPage() {
  return (
    <div className="space-y-3">
      <p className="text-sm uppercase tracking-[0.25em] text-[var(--primary)] font-semibold">
        Dashboard
      </p>

      <h1 className="text-4xl font-bold text-[var(--text)]">
        Welcome to GMU Lead Management ERP
      </h1>

      <p className="text-gray-500 max-w-2xl">
        This is the dashboard overview. KPI cards, charts, recent activities,
        and lead analytics will appear here in the next sprint.
      </p>
    </div>
  );
}