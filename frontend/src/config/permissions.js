export const PERMISSIONS = {
  ADMIN: {
    dashboard: true,

    leads: {
      view: true,
      create: true,
      edit: true,
      delete: true,
      assign: true,
    },

    people: {
      view: true,
      create: true,
      edit: true,
      delete: true,
    },

    departments: {
      view: true,
      create: true,
      edit: true,
      delete: true,
    },

    reports: {
      view: true,
      export: true,
    },

    settings: {
      view: true,
      update: true,
    },
  },

  COUNSELLOR: {
    dashboard: true,

    leads: {
      view: true,
      create: false,
      edit: true,
      delete: false,
      assign: false,
    },

    people: {
      view: false,
      create: false,
      edit: false,
      delete: false,
    },

    departments: {
      view: false,
      create: false,
      edit: false,
      delete: false,
    },

    reports: {
      view: false,
      export: false,
    },

    settings: {
      view: true,
      update: false,
    },
  },
};