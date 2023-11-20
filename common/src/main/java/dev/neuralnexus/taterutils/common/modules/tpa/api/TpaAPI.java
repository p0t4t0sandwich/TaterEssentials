package dev.neuralnexus.taterutils.common.modules.tpa.api;

import dev.neuralnexus.taterlib.common.Utils;
import dev.neuralnexus.taterlib.common.player.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/** API for the TPA module. */
public class TpaAPI {
    private final Data data;

    public TpaAPI() {
        data = new Data();
    }

    /**
     * Gets a request.
     *
     * @param sender The sender.
     * @return The request.
     */
    public Optional<TPRequest> getRequest(Player sender) {
        return data.getRequest(sender);
    }

    /**
     * Gets all the requests.
     *
     * @return The request.
     */
    public Set<TPRequest> getRequests() {
        return data.getRequests();
    }

    /**
     * Adds a request.
     *
     * @param player The player.
     * @param target The target.
     */
    public void addRequest(Player player, Player target) {
        data.addRequest(new TpaAPI.TPRequest(player, target));
    }

    /**
     * Removes a request.
     *
     * @param request The request.
     */
    public void removeRequest(TPRequest request) {
        data.removeRequest(request);
    }

    /**
     * Checks if a player has a request.
     *
     * @param player The player.
     * @return If the player has a request.
     */
    public boolean hasPendingRequest(Player player) {
        return data.getRequests().stream().anyMatch(request -> request.getSender().equals(player));
    }

    /**
     * Accepts a request.
     *
     * @param player The player.
     */
    public void acceptRequest(Player player) {
        data.getRequests().stream()
                .filter(request -> request.getReceiver().equals(player))
                .findFirst()
                .ifPresent(
                        request -> {
                            request.accept();
                            data.removeRequest(request);
                        });
    }

    /**
     * Denies a request.
     *
     * @param player The player.
     */
    public void denyRequest(Player player) {
        data.getRequests().stream()
                .filter(request -> request.getReceiver().equals(player))
                .findFirst()
                .ifPresent(
                        request -> {
                            request.deny();
                            data.removeRequest(request);
                        });
    }

    /** Checks if requests have expired, and removes them if they have. */
    public void checkExpired() {
        data.getRequests().stream()
                .filter(TPRequest::isExpired)
                .forEach(
                        request -> {
                            request.sendExpireMessage();
                            data.removeRequest(request);
                        });
    }

    /** A teleport request. */
    public static class TPRequest {
        private final long timestamp = System.currentTimeMillis();
        private final Player sender;
        private final Player receiver;

        /**
         * Creates a new TPRequest.
         *
         * @param sender The sender.
         * @param receiver The receiver.
         */
        public TPRequest(Player sender, Player receiver) {
            this.sender = sender;
            this.receiver = receiver;
        }

        /**
         * Gets the sender.
         *
         * @return The sender.
         */
        public Player getSender() {
            return sender;
        }

        /**
         * Gets the receiver.
         *
         * @return The receiver.
         */
        public Player getReceiver() {
            return receiver;
        }

        /**
         * Checks if the request is expired.
         *
         * @return If the request is expired.
         */
        public boolean isExpired() {
            return System.currentTimeMillis() - timestamp > 30000;
        }

        /** Send expire message to the sender. */
        public void sendExpireMessage() {
            sender.sendMessage(
                    Utils.substituteSectionSign(
                            "&aYour teleport request to &e"
                                    + receiver.getName()
                                    + " &ahas expired."));
        }

        /** Accepts the request. */
        public void accept() {
            sender.teleport(receiver);
            sender.sendMessage(
                    Utils.substituteSectionSign(
                            "&aYou have accepted the teleport request from &e"
                                    + receiver.getName()
                                    + "&a."));
            receiver.sendMessage(
                    Utils.substituteSectionSign(
                            "&e" + sender.getName() + " &ahas accepted your teleport request."));
        }

        /** Denies the request. */
        public void deny() {
            sender.sendMessage(
                    Utils.substituteSectionSign(
                            "&aYou have denied the teleport request from &e"
                                    + receiver.getName()
                                    + "&a."));
            receiver.sendMessage(
                    Utils.substituteSectionSign(
                            "&e" + sender.getName() + " &ahas denied your teleport request."));
        }
    }

    /** The data for the API. */
    private static class Data {
        private final Set<TPRequest> requests = new HashSet<>();

        /**
         * Gets a request.
         *
         * @param sender The sender.
         * @return The request.
         */
        public Optional<TPRequest> getRequest(Player sender) {
            return requests.stream()
                    .filter(request -> request.getSender().equals(sender))
                    .findFirst();
        }

        /**
         * Gets all the requests.
         *
         * @return The request.
         */
        public Set<TPRequest> getRequests() {
            return requests;
        }

        /**
         * Adds a request.
         *
         * @param request The request.
         */
        public void addRequest(TPRequest request) {
            requests.add(request);
        }

        /**
         * Removes a request.
         *
         * @param request The request.
         */
        public void removeRequest(TPRequest request) {
            requests.remove(request);
        }
    }
}
