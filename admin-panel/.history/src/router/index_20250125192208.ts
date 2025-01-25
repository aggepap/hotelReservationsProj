import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AddRoomView from "../views/rooms/AddRoomView.vue";
import AllRoomsView from "../views/rooms/AllRoomsView.vue";
import AddResidentView from "../views/residents/AddResidentView.vue";
import AllResidentsView from "@/views/residents/AllResidentsView.vue";
import AddReservationView from "@/views/reservations/AddReservationView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/rooms",
      name: "allRooms",
      component: AllRoomsView,
    },
    {
      path: "/rooms/add",
      name: "addRoom",
      component: AddRoomView,
    },
    {
      path: "/residents",
      name: "allResidents",
      component: AllResidentsView,
    },
    {
      path: "/residents/add",
      name: "addResident",
      component: AddResidentView,
    },
    {
      path: "/reservations/add",
      name: "addResident",
      component: AddReservationView,
    },

    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/AboutView.vue"),
    },
  ],
});

export default router;
