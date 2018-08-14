package io.sailrocket.core.machine;

import java.util.function.Predicate;

class Transition {
   private final Predicate<Session> condition;
   private final Action action;
   private final boolean blocking;
   private final State next;

   Transition(Predicate<Session> condition, Action action, State next, boolean blocking) {
      this.condition = condition;
      this.action = action;
      this.next = next;
      this.blocking = blocking;
   }

   boolean test(Session session) {
      return condition == null || condition.test(session);
   }

   State invoke(Session session) {
      if (action != null) {
         action.invoke(session);
      }
      return next;
   }

   public boolean isBlocking() {
      return blocking;
   }
}
