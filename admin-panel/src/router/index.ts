import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AddRoomView from "../views/rooms/AddRoomView.vue";
import AllRoomsView from "../views/rooms/AllRoomsView.vue";
import AddResidentView from "../views/residents/AddResidentView.vue";
import AllResidentsView from "@/views/residents/AllResidentsView.vue";
import AddReservationView from "../views/reservations/AddReservationView.vue";
import AllReservationsView from "@/views/reservations/AllReservationsView.vue";
import SingleReservationView from "../views/reservations/SingleReservationView.vue";
import SingleResidentView from "@/views/residents/SingleResidentView.vue";
import NotFound from "../components/NotFound.vue";
const router = createRouter({
  linkActiveClass:
    "border-l-8 border-blue-200  hover:bg-gray-100 hover:text-black",
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
      path: "/residents/:id",
      name: "singleResident",
      component: SingleResidentView,
      props: true,
    },
    {
      path: "/residents/add",
      name: "addResident",
      component: AddResidentView,
    },
    {
      path: "/reservations",
      name: "AllReservations",
      component: AllReservationsView,
    },
    {
      path: "/reservations/add",
      name: "addReservation",
      component: AddReservationView,
    },
    {
      path: "/reservations/:id",
      name: "singleReservation",
      component: SingleReservationView,
      props: true,
    },
    { path: "/:catchAll(.*)", name: "NotFound", component: NotFound },
  ],
});

export default router;
